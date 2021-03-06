package im.heart.oauth2.web;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import im.heart.core.web.AbstractController;
import im.heart.usercore.entity.FrameUserConnect;
import im.heart.core.enums.IdentityType;
import im.heart.usercore.service.FrameUserConnectService;
import im.heart.usercore.service.FrameUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
public class WeChatMaController extends AbstractAuthController {
    private Logger logger = LoggerFactory.getLogger(WeChatMaController.class);
    @Autowired
    private WxMaService wxMaService;

    @RequestMapping("/wx/user/sessionInfo")
    public ModelAndView getSessionInfo (String code){
        try{

            WxMaJscode2SessionResult sessionInfo= this.wxMaService.jsCode2SessionInfo(code);
            Optional<FrameUserConnect> frameUserConnectOpt=this.userConnectService.findByOpenIdAndType(sessionInfo.getOpenid(), IdentityType.wechat);
            if(frameUserConnectOpt.isPresent()){

            }
        }catch (WxErrorException e){
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        }
      return null;
    }
}