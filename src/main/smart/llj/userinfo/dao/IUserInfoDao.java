package main.smart.llj.userinfo.dao;

import main.smart.llj.base.dao.IDao;
import main.smart.llj.userinfo.domain.UserInfo;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Grant on 16/4/25.
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@Repository("userInfoDao")
public interface IUserInfoDao extends IDao {
    public List<UserInfo> findall();
    public Integer saveUser(UserInfo userInfo);
    public List<UserInfo> queryForUserByIdnum(String str);
    public List<UserInfo> queryForUserByPhone(String str);
    public List<UserInfo> queryForUserByMail(String str);

}
