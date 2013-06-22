package com.baidu.push.handler;

import com.baidu.push.model.Empty;

/**
 * 空返回结果的处理类
 * @author liyazhou@baidu.com
 *
 */
public class VoidResponseHandler extends HttpResponseHandler<Empty> {
	@Override
	protected boolean hasResponseParams() {
		return false;
	}

}
