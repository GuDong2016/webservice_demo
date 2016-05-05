package main.smart.llj.loginfo.service;

import main.smart.llj.base.service.IService;
import main.smart.llj.loginfo.domain.LogInfo;

/**
 * Created by dell on 2016/4/27.
 */
public interface ILogInfoService extends IService {
    public int saveLogInfo(LogInfo logInfo);
}
