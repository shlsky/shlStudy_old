package com.shl.thriftstudy.server;

import com.facebook.fb303.fb_status;
import org.apache.thrift.TException;

import java.util.Map;

/**
 * Created by jackson on 16/4/5.
 */
public class FB303 implements com.facebook.fb303.FacebookService.Iface {
    @Override
    public String getName() throws TException {
        return null;
    }

    @Override
    public String getVersion() throws TException {
        return null;
    }

    @Override
    public fb_status getStatus() throws TException {
        return null;
    }

    @Override
    public String getStatusDetails() throws TException {
        return null;
    }

    @Override
    public Map<String, Long> getCounters() throws TException {
        return null;
    }

    @Override
    public long getCounter(String key) throws TException {
        return 0;
    }

    @Override
    public void setOption(String key, String value) throws TException {

    }

    @Override
    public String getOption(String key) throws TException {
        return null;
    }

    @Override
    public Map<String, String> getOptions() throws TException {
        return null;
    }

    @Override
    public String getCpuProfile(int profileDurationInSec) throws TException {
        return null;
    }

    @Override
    public long aliveSince() throws TException {
        return 0;
    }

    @Override
    public void reinitialize() throws TException {

    }

    @Override
    public void shutdown() throws TException {

    }
}
