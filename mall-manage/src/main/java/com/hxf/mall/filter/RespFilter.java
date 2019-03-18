package com.hxf.mall.filter;

import com.alibaba.fastjson.JSON;
import com.hxf.mall.to.AMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

@Component
@Order(1)
@WebFilter(filterName = "errorMsgFilter", urlPatterns = "/*")
public class RespFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        RespMsgFilterResponseWrapper wrapper = new RespMsgFilterResponseWrapper(response);

        filterChain.doFilter(servletRequest, wrapper);
        String result = wrapper.getResponseData(response.getCharacterEncoding());
        //...gono

        try
        {
            //封装成指定格式返回数据
            AMessage aMessage = new AMessage();
            aMessage.setCode(response.getStatus());
            aMessage.setData(JSON.parse(result));
            result = JSON.toJSONString(aMessage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //把返回值输出到客户端
        ServletOutputStream out = response.getOutputStream();
        out.write(result.getBytes());
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {

    }


    class RespMsgFilterResponseWrapper extends HttpServletResponseWrapper {
        private ByteArrayOutputStream buffer = null;
        private ServletOutputStream out = null;
        private PrintWriter writer = null;

        public RespMsgFilterResponseWrapper(HttpServletResponse response) throws IOException {
            super(response);
            buffer = new ByteArrayOutputStream();
            out = new WapperedOutputStream(buffer);
            writer = new PrintWriter(new OutputStreamWriter(buffer, "UTF-8"));
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return out;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return writer;
        }

        @Override
        public void flushBuffer() throws IOException {
            if (out != null) {
                out.flush();
            }
            if (writer != null) {
                writer.flush();
            }
        }

        @Override
        public void reset() {
            buffer.reset();
        }

        public String getResponseData(String charset) throws IOException {
            flushBuffer();
            byte[] bytes = buffer.toByteArray();
            try {
                return new String(bytes, charset);
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }

        class WapperedOutputStream extends ServletOutputStream {
            private ByteArrayOutputStream bos = null;

            public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
                bos = stream;
            }

            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }
        }
    }
}