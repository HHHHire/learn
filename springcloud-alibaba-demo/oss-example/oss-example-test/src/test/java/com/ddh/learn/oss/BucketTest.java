package com.ddh.learn.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/10 0:02
 * 存储类型测试
 */
@SpringBootTest(classes = {OssExampleTestApplication.class})
@RunWith(SpringRunner.class)
public class BucketTest {

    @Autowired
    private OSS ossClient;

    private String bucketName = "hhhhire";

    /**
     * 创建存储空间bucket，暂未测试
     */
    @Test
    public void createBucket() {
        String bucketName = "hhhhire01";
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 设置存储空间的存储类型为标准存储
        createBucketRequest.setStorageClass(StorageClass.Standard);
        // 设置数据容灾类型，默认为本地容灾为LRS，同城容灾为ZRS
//        createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS);
        // 设置存储空间的访问权限，默认是私有，这里是公共可读
        createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
        ossClient.createBucket(createBucketRequest);
    }

    /**
     * 获取存储空间列表
     */
    @Test
    public void getBucketList() {
        // 获取所有
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }
        // 获取指定前缀的存储空间
        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
        listBucketsRequest.setPrefix("hhhh");
        BucketList bucketList = ossClient.listBuckets(listBucketsRequest);
        for (Bucket bucket : bucketList.getBucketList()) {
            System.out.println("-" + bucket.getName());
        }
    }

    /**
     * 获取存储空间的地域，oss-cn-shanghai
     */
    @Test
    public void getBucketLocation() {
        String bucketLocation = ossClient.getBucketLocation(bucketName);
        System.out.println(bucketLocation);
    }

    /**
     * 获取存储空间的信息
     */
    @Test
    public void getBucketInfo() {
        BucketInfo bucketInfo = ossClient.getBucketInfo(bucketName);
        System.out.println("访问权限："+bucketInfo.getCannedACL());
        System.out.println("拥有者："+bucketInfo.getBucket().getOwner());
        System.out.println("容灾类型："+bucketInfo.getDataRedundancyType());
    }

    /**
     * 获取更改存储空间的访问权限
     */
    @Test
    public void updateBucketAcl() {
        // 获取访问权限
        AccessControlList bucketAcl = ossClient.getBucketAcl(bucketName);
        System.out.println(bucketAcl.toString());

        ossClient.setBucketAcl(bucketName, CannedAccessControlList.Private);
    }

    /**
     * 标签操作
     */
    @Test
    public void setTagBucket() {
        // 设置标签
        SetBucketTaggingRequest setBucketTaggingRequest = new SetBucketTaggingRequest(bucketName);
        setBucketTaggingRequest.setTag("name", "zhangsan");
        setBucketTaggingRequest.setTag("age", "18");
        ossClient.setBucketTagging(setBucketTaggingRequest);

        // 获取Bucket的标签
        TagSet bucketTagging = ossClient.getBucketTagging(bucketName);
        System.out.println(bucketTagging);
        System.out.println("-------------");

        // 获取指定标签的Bucket
        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
        listBucketsRequest.setTag("age", "18");
        BucketList bucketList = ossClient.listBuckets(listBucketsRequest);
        for (Bucket bucket : bucketList.getBucketList()) {
            System.out.println(bucket.getName());
        }
        System.out.println("-------------");

        // 删除标签
        ossClient.deleteBucketTagging(new GenericRequest(bucketName));
        bucketTagging = ossClient.getBucketTagging(bucketName);
        System.out.println(bucketTagging);
    }

    /**
     * 授权策略
     */
    @Test
    public void setPolicy() {
        // 设置授权策略
        String policy = "{\"Statement\": [{\"Effect\": \"Allow\", \"Action\": [\"oss:GetObject\", \"oss:ListObjects\"], \"Resource\": [\"acs:oss:*:*:*/user1/*\"]}], \"Version\": \"1\"}";
        ossClient.setBucketPolicy(bucketName, policy);
        // 获取授权策略
        GetBucketPolicyResult bucketPolicy = ossClient.getBucketPolicy(bucketName);
        System.out.println(bucketPolicy.getPolicyText());
        // 删除
        ossClient.deleteBucketPolicy(bucketName);
    }
}
