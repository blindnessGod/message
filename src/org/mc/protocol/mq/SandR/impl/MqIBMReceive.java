/**
 * 
 */
package org.mc.protocol.mq.SandR.impl;

import java.util.Hashtable;

import org.mc.protocol.mq.SandR.IConnectionReceive;
import org.mc.protocol.mq.SandR.MqManagerABS;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPoolToken;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

/**   
 *    
 * 项目名称：MessageComponent   
 * 类名称：MqIBMReceive   
 * 类描述：   
 * 创建人：王开群
 * 创建时间：2014-3-6  
 * @version    
 *    
 */
public class MqIBMReceive extends MqManagerABS implements IConnectionReceive {
	/**
	 * MQ执行属性
	 */
	private MQPoolToken token = null;
	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionReceive#Connect()
	 */
	@Override
	public Object Connect() throws Exception {
		// TODO Auto-generated method stub
		MQEnvironment.hostname = super.receiverQueue.getHostName(); 
		MQEnvironment.channel = super.receiverQueue.getChannel();
		MQEnvironment.port = Integer.parseInt(super.receiverQueue.getPort());
		MQEnvironment.CCSID = Integer.parseInt(super.receiverQueue.getCCSID());
		
		gmo = new MQGetMessageOptions();
		gmo.options = MQConstants.MQGMO_WAIT
				| MQConstants.MQGMO_FAIL_IF_QUIESCING;// 1
		gmo.waitInterval = super.receiverQueue.getWaitInterval();
		token = MQEnvironment.addConnectionPoolToken();
		return new MQQueueManager(super.receiverQueue.getQueueManager());
	}

	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionReceive#disconnect()
	 */
	@Override
	public void disconnect() throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionReceive#getMsg()
	 */
	@Override
	public byte[] getMsg() throws Exception {
		// TODO Auto-generated method stub
		int receiveOptions = MQConstants.MQOO_INPUT_AS_Q_DEF
				| MQConstants.MQOO_INQUIRE | MQConstants.MQOO_OUTPUT;
		MQQueueManager queueManager = null;
		MQQueue queue = null;
		MQMessage rcvMsg = new MQMessage();
		byte data[];
		try {
			queueManager = (MQQueueManager) Connect();

			queue = queueManager.accessQueue(super.receiverQueue.getQueueName(),
					receiveOptions);
			queue.getMaximumDepth();
			queue.get(rcvMsg, gmo);

			data = new byte[rcvMsg.getDataLength()];
			rcvMsg.readFully(data);

			gmo = null;
			rcvMsg = null;
			queueManager.disconnect();
			queueManager = null;
			queue = null;
			return data;
		} catch (MQException ex) {
			if (ex.reasonCode != 2033) {
				ex.printStackTrace();
			}
			return null;
		} finally {
			try {
				if(this.token != null) {
					MQEnvironment.removeConnectionPoolToken(this.token);
				}
				if (null != queueManager) {
					queueManager.disconnect();
				}
			} catch (MQException ex) {
				if (ex.reasonCode != 2033) {
					ex.printStackTrace();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.protocol.mq.SandR.IConnectionReceive#release()
	 */
	@Override
	public void release() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDeep() throws Exception {
		// TODO Auto-generated method stub
		int receiveOptions = MQConstants.MQOO_INPUT_AS_Q_DEF
		| MQConstants.MQOO_INQUIRE | MQConstants.MQOO_OUTPUT;
		MQQueueManager queueManager = null;
		MQQueue queue = null;
		MQMessage rcvMsg = new MQMessage();
		byte data[];
		try {
			queueManager = (MQQueueManager) Connect();
		
			queue = queueManager.accessQueue(super.receiverQueue.getQueueName(),
					receiveOptions);
			return queue.getMaximumDepth();
		} catch (MQException ex) {
			if (ex.reasonCode != 2033) {
				ex.printStackTrace();
				try {
					Thread.sleep(2 * 1000);
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
			return 0;
		} finally {
			try {
				if (null != queueManager) {
					queueManager.disconnect();
				}
			} catch (MQException ex) {
				if (ex.reasonCode != 2033) {
					ex.printStackTrace();
				}
			}
		}
	}

}
