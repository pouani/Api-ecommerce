package com.stage.ecommerce.interceptor;

import org.hibernate.EmptyInterceptor;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql){
        return super.onPrepareStatement(sql);
    }
}
