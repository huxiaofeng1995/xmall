package com.hxf.mall.listener;

import com.hxf.mall.test.Progress;
import com.hxf.mall.test.ProgressSingleton;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class FileUploadProgressListener implements ProgressListener {

    private HttpSession session;

    public void setSession(HttpSession session){
        this.session = session;
        Progress status = new Progress();
        session.setAttribute("status", status);
        ProgressSingleton.put("status",status);
    }

    @Override
    public void update(long read, long length, int items) {
        System.out.println("update-------------------------------");
        //Progress status = (Progress) session.getAttribute("status");
        Progress status = (Progress) ProgressSingleton.get("status");
        status.setRead(read);
        status.setLength(length);
        status.setItems(items);
        System.out.println(status + "listener");
    }
}


