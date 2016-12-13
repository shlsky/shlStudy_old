package com.shl.aop.dynamicProxy;

import com.shl.aop.dynamicProxy.ISayhelloService;
import org.springframework.stereotype.Service;

/**
 * Created by jackson on 16/4/6.
 */

@Service(value = "sayhelloInstance")
public class SayhelloServiceImpl implements ISayhelloService {
    @Override
    public void sayhello2all() {
        System.out.println("hell world");
    }

    @Override
    public void sayhell2one(String name ) {
        System.out.println("hello "+name);
    }
}
