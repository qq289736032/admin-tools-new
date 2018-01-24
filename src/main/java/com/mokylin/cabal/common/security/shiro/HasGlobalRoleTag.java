package com.mokylin.cabal.common.security.shiro;

import com.mokylin.cabal.modules.sys.entity.Role;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import org.apache.shiro.web.tags.RoleTag;
import org.apache.shiro.web.tags.SecureTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/5 14:32
 * 项目: cabal-tools
 * 登陆的用户包含的角色其中一个是否具有全部平台权限
 */
public class HasGlobalRoleTag extends SecureTag {

    private static final Logger log = LoggerFactory.getLogger(HasGlobalRoleTag.class);

    @Override
    public int onDoStartTag() throws JspException {
        List<Role> roleList = UserUtils.getUser().getRoleList();
        if (roleList != null) {
            for(Role role : roleList){
                if(role.hasAllPlatformPermission()){
                    return TagSupport.EVAL_BODY_INCLUDE;
                }
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Subject does not exist or has no Role hasAllPlatformPermission.  Tag body will not be evaluated.");
            }
            return TagSupport.SKIP_BODY;
        }
        return TagSupport.SKIP_BODY;
    }
}
