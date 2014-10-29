package org.mc.protocol.mq.SandR;

import java.util.Hashtable;

import org.mc.protocol.config.ConstantValue;

import com.framework.component.config.LoadMsgConfig;
import com.framework.component.config.ConfigObj.ReceiverQueue;
import com.framework.component.config.ConfigObj.SenderQueue;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQQueueManager;

public abstract class MqManagerABS {
	protected static final Hashtable htTmpConfig = LoadMsgConfig.getInstance(ConstantValue.configName).getHtConfigReceiver();
	protected static final ReceiverQueue receiverQueue = (ReceiverQueue) htTmpConfig
			.get("MqReceive");
	protected static final SenderQueue senderQueue = (SenderQueue) htTmpConfig
			.get("MqSender");
	protected Hashtable MqProperties;
	protected MQGetMessageOptions gmo = new MQGetMessageOptions();
}
