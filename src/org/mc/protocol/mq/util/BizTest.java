package org.mc.protocol.mq.util;

import org.mc.protocol.biz.Interface.IBizReceiver;

public class BizTest implements IBizReceiver {

	@Override
	public void BizProc(byte[] ed) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(new String(ed));
	}

}
