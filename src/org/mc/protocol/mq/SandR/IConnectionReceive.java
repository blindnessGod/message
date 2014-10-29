/**
 * 
 */
package org.mc.protocol.mq.SandR;

import com.ibm.mq.MQQueueManager;

/**   
 *    
 * ��Ŀ���ƣ�MessageComponent   
 * �����ƣ�IConnectionReceive   
 * ��������   ������Ϣ�ӿ�
 * �����ˣ�����Ⱥ
 * ����ʱ�䣺2014-3-6  
 * @version    
 *    
 */
public interface IConnectionReceive {
	/**
	 * ����
	 * 
	 * @throws Exception
	 */
	public void release() throws Exception;

	/**
	 * �Ͽ�����
	 * 
	 * @throws Exception
	 */
	public void disconnect() throws Exception;

	/**
	 * ��������
	 * 
	 * @throws Exception
	 */
	public Object Connect() throws Exception;
	/**
	 * �õ���Ϣ
	 * 
	 * @return �����ֽ�����
	 * @throws Exception
	 */
	public byte[] getMsg() throws Exception;
	/**
	 * ��ö������
	 * @return �������ֵ
	 * @throws Exception
	 */
	public int getDeep() throws Exception;
}
