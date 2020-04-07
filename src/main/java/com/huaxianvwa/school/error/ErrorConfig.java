package com.huaxianvwa.school.error;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 我们这个项目中，其实只有 index.html 这一个页面，
 * 所有的其它内容都是在这个页面里动态渲染的。
 * 当我们直接在后端访问 /login 路径时，
 * 服务器会后端并没有这个路径对应的内容，
 * 所以返回了 Error Page。
 * 为了获取到我们需要的内容，我们要想办法触发前端路由，
 * 即在后端添加处理内容，把 通过这个 URL 渲染出的 index.html 
 * 返回到浏览器。
*/
@Component
public class ErrorConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		 ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
	        registry.addErrorPages(error404Page);
	}

}
