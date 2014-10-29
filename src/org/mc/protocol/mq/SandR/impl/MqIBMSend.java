/**
 * 
 */
package org.mc.protocol.mq.SandR.impl;

import java.util.Hashtable;

import org.mc.protocol.mq.SandR.IConnectionSend;
import org.mc.protocol.mq.SandR.MqManagerABS;

import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

/**   
 *    
 * 项目名称：MessageComponent   
 * 类名称：MqIBMSend   
 * 类描述：   
 * 创建人：王开群
 * 创建时间：2014-3-6  
 * @version    
 *    
 */
public class MqIBMSend extends MqManagerABS implements IConnectionSend {

	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionSend#Connect()
	 */
	@Override
	public Object Connect() throws Exception {
		// TODO Auto-generated method stub
		MqProperties = new Hashtable();
		MqProperties.put(MQConstants.HOST_NAME_PROPERTY, super.senderQueue.getHostName());
		MqProperties.put(MQConstants.PORT_PROPERTY, super.senderQueue.getPort());
		MqProperties.put(MQConstants.CHANNEL_PROPERTY, super.senderQueue.getChannel());
		MqProperties.put(MQConstants.CCSID_PROPERTY, super.senderQueue.getCCSID());

		gmo = new MQGetMessageOptions();
		gmo.options = MQConstants.MQGMO_WAIT
				| MQConstants.MQGMO_FAIL_IF_QUIESCING;// 1
		gmo.waitInterval = super.receiverQueue.getWaitInterval();

		return new MQQueueManager(super.receiverQueue.getQueueManager(), MqProperties);
	}

	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionSend#disconnect()
	 */
	@Override
	public void disconnect() throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionSend#putMsg(byte[])
	 */
	@Override
	public void putMsg(byte[] ed) throws Exception {
		MQMessage msg = new MQMessage();
		int i = 0;
		MQQueue q = null;
		int openOption = 0;
		openOption = MQConstants.MQOO_OUTPUT
				| MQConstants.MQOO_FAIL_IF_QUIESCING;
		MQQueueManager queueManager = null;

		try {
			queueManager = (MQQueueManager) Connect();

			q = queueManager.accessQueue(super.senderQueue.getQueueName(), openOption);
			msg.write(ed);
			q.put(msg);
			queueManager.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (queueManager != null) {
				queueManager.disconnect();
//				MQEnvironment.removeConnectionPoolToken(token);
			}
		}
		try {
			Thread.sleep(6 * 1000);
		} catch (Exception e) {
		}
	}

	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionSend#release()
	 */
	@Override
	public void release() throws Exception {
		// TODO Auto-generated method stub

	}

}
