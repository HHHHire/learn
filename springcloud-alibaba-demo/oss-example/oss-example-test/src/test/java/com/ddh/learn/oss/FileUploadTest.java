package com.ddh.learn.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.model.*;
import com.ddh.learn.oss.config.OssConfig;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/10 16:42
 * 文件上传测试
 */
@SpringBootTest(classes = {OssExampleTestApplication.class})
@RunWith(SpringRunner.class)
public class FileUploadTest {

    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private OSS ossClient;

    private String bucketName = "hhhhire";

    /**
     * 简单上传，文件流
     */
    @Test
    public void simpleUploadFileStream() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("F:\\note\\images\\k8s7.png"));
            ossClient.putObject(bucketName, "testFile.png", fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单上传，文件
     */
    @Test
    public void simpleUploadFileFile() {
        PutObjectRequest request = new PutObjectRequest(bucketName, "testFile2.png", new File("F:\\note\\images\\k8s7.png"));
        // 上传时可以设置存储类型和访问权限
        /*ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        metadata.setObjectAcl(CannedAccessControlList.Private);
        request.setMetadata(metadata);*/

        ossClient.putObject(request);
    }

    /**
     * 表单上传，官网demo
     * todo: 无法测通
     */
    @Test
    public void postObject() throws Exception {
        String objectName = "testFile2.png";
        String localFilePath = "F:\\note\\images\\k8s7.png";
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        // 在URL中添加存储空间名称，添加后URL如下：http://yourBucketName.oss-cn-hangzhou.aliyuncs.com。
        String urlStr = "http://" + bucketName + "." + endpoint;
        // 设置表单Map。
        Map<String, String> formFields = new LinkedHashMap<>();
        // 设置文件名称。
        formFields.put("key", objectName);
        // 设置Content-Disposition。
        formFields.put("Content-Disposition", "attachment;filename="
                + localFilePath);
        // 设置回调参数。
        Callback callback = new Callback();
        // 设置回调服务器地址，如http://oss-demo.aliyuncs.com:23450或http://127.0.0.1:9090。
        callback.setCallbackUrl("http://127.0.0.1:9090");
        // 设置回调请求消息头中Host的值，如oss-cn-hangzhou.aliyuncs.com。
        callback.setCallbackHost(endpoint);
        // 设置发起回调时请求body的值。
        callback.setCallbackBody("{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}");
        // 设置发起回调请求的Content-Type。
        callback.setCalbackBodyType(Callback.CalbackBodyType.JSON);
        // 设置发起回调请求的自定义参数，由Key和Value组成，Key必须以x:开始，且必须小写。
        callback.addCallbackVar("x:var1", "value1");
        callback.addCallbackVar("x:var2", "value2");
        // 在表单Map中设置回调参数。
        setCallBack(formFields, callback);
        // 设置OSSAccessKeyId。
        formFields.put("OSSAccessKeyId", accessKeyId);
        String policy = "{\"expiration\": \"2120-01-01T12:00:00.000Z\",\"conditions\": [[\"content-length-range\", 0, 104857600]]}";
        String encodePolicy = new String(Base64.encodeBase64(policy.getBytes()));
        // 设置policy。
        formFields.put("policy", encodePolicy);
        // 生成签名。
        String signaturecom = com.aliyun.oss.common.auth.ServiceSignature.create().computeSignature(accessKeySecret, encodePolicy);
        // 设置签名。
        formFields.put("Signature", signaturecom);
        String ret = formUpload(urlStr, formFields, localFilePath);
        System.out.println("Post Object [" + objectName + "] to bucket [" + bucketName + "]");
        System.out.println("post reponse:" + ret);
    }

    private static String formUpload(String urlStr, Map<String, String> formFields, String localFile)
            throws Exception {
        String res = "";
        HttpURLConnection conn = null;
        String boundary = "9431149156168";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            // 设置MD5值。MD5值由整个body计算得出。
            conn.setRequestProperty("Content-MD5", "<yourContentMD5>");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 遍历读取表单Map中的数据，将数据写入到输出流中。
            if (formFields != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator<Map.Entry<String, String>> iter = formFields.entrySet().iterator();
                int i = 0;
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    if (i == 0) {
                        strBuf.append("--").append(boundary).append("\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                                + inputName + "\"\r\n\r\n");
                        strBuf.append(inputValue);
                    } else {
                        strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                                + inputName + "\"\r\n\r\n");
                        strBuf.append(inputValue);
                    }
                    i++;
                }
                out.write(strBuf.toString().getBytes());
            }
            // 读取文件信息，将要上传的文件写入到输出流中。
            File file = new File(localFile);
            String filename = file.getName();
            String contentType = new MimetypesFileTypeMap().getContentType(file);
            if (contentType == null || contentType.equals("")) {
                contentType = "application/octet-stream";
            }
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(boundary)
                    .append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=\"file\"; "
                    + "filename=\"" + filename + "\"\r\n");
            strBuf.append("Content-Type: " + contentType + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            // 读取返回数据。
            strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.err.println("Send post request exception: " + e);
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    private static void setCallBack(Map<String, String> formFields, Callback callback) {
        if (callback != null) {
            String jsonCb = OSSUtils.jsonizeCallback(callback);
            String base64Cb = BinaryUtil.toBase64String(jsonCb.getBytes());
            formFields.put("callback", base64Cb);
            if (callback.hasCallbackVar()) {
                Map<String, String> varMap = callback.getCallbackVar();
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    formFields.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * 文件的追加
     */
    @Test
    public void appendObject() {
        String content1 = "hello world 01";
        String content2 = "hello world 02";
        String content3 = "hello world 03";

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("text/plain");
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, "appendTest2", new ByteArrayInputStream(content1.getBytes()), metadata);

        // 通过AppendObjectRequest设置单个参数。
        // 设置存储空间名称。
        //appendObjectRequest.setBucketName("<yourBucketName>");
        // 设置文件名称。
        //appendObjectRequest.setKey("<yourObjectName>");
        // 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为InputStream类型。
        //appendObjectRequest.setInputStream(new ByteArrayInputStream(content1.getBytes()));
        // 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为File类型。
        //appendObjectRequest.setFile(new File("<yourLocalFile>"));
        // 指定文件的元信息，第一次追加时有效。
        //appendObjectRequest.setMetadata(meta);

        // 第一次追加。
        // 设置文件的追加位置。
        appendObjectRequest.setPosition(0L);
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
        // 文件的64位CRC值。此值根据ECMA-182标准计算得出。
        System.out.println(appendObjectResult.getObjectCRC());

        // 第二次追加。
        // nextPosition指明下一次请求中应当提供的Position，即文件当前的长度。
        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
        appendObjectRequest.setInputStream(new ByteArrayInputStream(content2.getBytes()));
        appendObjectResult = ossClient.appendObject(appendObjectRequest);

        // 第三次追加。
        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
        appendObjectRequest.setInputStream(new ByteArrayInputStream(content3.getBytes()));
        appendObjectResult = ossClient.appendObject(appendObjectRequest);
    }

    /**
     * 分片上传
     */
    @Test
    public void multipartUpload() throws IOException {
        String objectName = "multipartTest2";

        // 创建InitiateMultipartUploadRequest对象。
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);

        // 如果需要在初始化分片时设置文件存储类型，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // request.setObjectMetadata(metadata);

        // 初始化分片。
        InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个uploadId发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = upresult.getUploadId();

        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags = new ArrayList<>();
        // 计算文件有多少个分片。
        final long partSize = 1024 * 1024L;   // 1MB
        final File sampleFile = new File("F:\\BaiduNetdiskDownload\\暴风激活17.0.zip");
        long fileLength = sampleFile.length();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        // 遍历分片上传。
        for (int i = 0; i < partCount; i++) {
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream instream = new FileInputStream(sampleFile);
            // 跳过已经上传的分片。
            instream.skip(startPos);
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(objectName);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(instream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber( i + 1);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果包含PartETag。PartETag将被保存在partETags中。
            partETags.add(uploadPartResult.getPartETag());
        }


        // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);

        // 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
        // completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);

        // 完成上传。
        ossClient.completeMultipartUpload(completeMultipartUploadRequest);

    }
}
