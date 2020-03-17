package cn.edu.jxust.sort.service.impl;

import cn.edu.jxust.sort.entity.po.Token;
import cn.edu.jxust.sort.repository.TokenRepository;
import cn.edu.jxust.sort.service.TokenSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: ddh
 * @data: 2020/1/5 21:14
 * @description
 **/
@Service
public class TokenServiceImpl implements TokenSerivce {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token getTokenById(String userId) {
        return tokenRepository.findById(userId).orElse(null);
    }

    @Override
    public Token createToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Integer deleteTokenByUserId(String userId) {
        return tokenRepository.deleteByUserId(userId);
    }

    @Override
    public boolean isExist(String token) {
        Optional<Token> token1 = tokenRepository.findByUserToken(token);
        return token1.isPresent();
    }
}
