/**
 * 
 */
package org.mc.protocol.mq.SandR;

/**   
 *    
 * ��Ŀ���ƣ�MessageComponent   
 * �����ƣ�IConnectionSend   
 * ��������   ������Ϣ�ӿ�
 * �����ˣ�����Ⱥ
 * ����ʱ�䣺2014-3-6  
 * @version    
 *    
 */
public interface IConnectionSend {
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
	 * ������Ϣ
	 * 
	 * @return �����ֽ�����
	 * @throws Exception
	 */
	public void putMsg( byte[] edi) throws Exception;
}
