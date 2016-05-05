package main.smart.llj.vcode.service;

import main.smart.llj.base.service.IService;
import main.smart.llj.vcode.domain.VCode;

/**
 * Created by dell on 2016/5/3.
 */
public interface IVCodeService extends IService {
    public int saveVCode(VCode vCode);

}
