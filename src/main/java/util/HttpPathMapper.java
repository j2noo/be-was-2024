package util;

import dto.HttpRequest;
import dto.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static logic.Logics.*;
import static util.constant.StringConstants.*;

public class HttpPathMapper {
    private static final Logger logger = LoggerFactory.getLogger(HttpPathMapper.class);
    private static final List<String> needLoginList = new ArrayList<>(List.of("/write.html"));


    public HttpResponse map(HttpRequest httpRequest,String userId) throws IOException {
        logger.info("httpRequest.getPath() = " + httpRequest.getPath());
        if (needLoginList.contains(httpRequest.getPath()) && userId==null) { // & userId로 얻은user가 올바른유저가아니면!
            System.out.println("!@#$%#@!!@#!@#!@#!@#@#!@#!@#!@#!@#!@!@#");
            return HttpResponse.redirectToMain();
        }
        return switch (httpRequest.getPath()) {
            case PATH_CREATE -> create(httpRequest);
            case PATH_LOGIN -> login(httpRequest);
            case PATH_LOGOUT -> logout(httpRequest);
            case USER_LIST -> getUserList(httpRequest,userId);
            default -> staticResponse(httpRequest, userId);

        };

    }

    private boolean isNeedLoginSession(HttpRequest httpRequest,String userId) {
        return needLoginList.contains(httpRequest.getPath());
    }

}
