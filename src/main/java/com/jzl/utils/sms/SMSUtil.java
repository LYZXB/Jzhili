package com.jzl.utils.sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jzl.beans.common.SMSTemplateParam;
import com.jzl.utils.PropertiesUtils;

/**
 * 阿里云提供demo
 * @ClassName: SMSUtil
 * @Description: 短信发送工具类
 * @author Sombra
 * @date 2018年3月27日 下午5:25:29
 * @version V1.0
 */
public class SMSUtil {
	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	// accessKey
	static final String accessKeyId = "LTAIhK3W3kU0Uoq3";
	static final String accessKeySecret = "yMEnJNHiFdkCUmxQampMnz7zJTpaxy";

	/**
	 * 
	 * @Title: sendSms  
	 * @Description: 发送短信  
	 * @param templateParam 模板参数对象
	 * @param phoneNumbers 电话号码
	 * @return
	 * @throws ClientException  SendSmsResponse    返回类型 
	 */
	public static SendSmsResponse sendSms(SMSTemplateParam templateParam) throws ClientException {

		Properties properties = PropertiesUtils.loadProperties("sms.properties");
		
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", properties.getProperty("sms.timeOut"));
		System.setProperty("sun.net.client.defaultReadTimeout", properties.getProperty("sms.timeOut"));

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(templateParam.getPhone());
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(properties.getProperty("sms.signName"));
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(properties.getProperty("sms.templateCode"));
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		request.setTemplateParam(gson.toJson(templateParam));

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId(properties.getProperty("sms.outId"));

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}

	/**
	 * 
	 * @Title: querySendDetails  
	 * @Description: 查询发送明细
	 * @param bizId 流水号 可填null
	 * @param phoneNumber 手机号码 必填
	 * @param date 日期 yyyyMMdd格式 必填
	 * @return
	 * @throws ClientException  QuerySendDetailsResponse    返回类型 
	 */
	public static QuerySendDetailsResponse querySendDetails(String bizId, String phoneNumber, String date) throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		// 必填-号码
		request.setPhoneNumber("15013720402");
		// 可选-流水号
		request.setBizId(bizId);
		// 必填-发送日期 支持30天内记录查询，格式yyyyMMdd
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(ft.format(new Date()));
		// 必填-页大小
		request.setPageSize(100L);
		// 必填-当前页码从1开始计数
		request.setCurrentPage(1L);

		// hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

		return querySendDetailsResponse;
	}

	public static void main(String[] args) throws ClientException, InterruptedException {

		SMSTemplateParam param = new SMSTemplateParam();
		param.setName("test");
		param.setPhone("15013720402");
		// 发短信
		SendSmsResponse response = sendSms(param);
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());

		Thread.sleep(3000L);

		/*// 查明细
		if (response.getCode() != null && response.getCode().equals("OK")) {
			QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
			System.out.println("短信明细查询接口返回数据----------------");
			System.out.println("Code=" + querySendDetailsResponse.getCode());
			System.out.println("Message=" + querySendDetailsResponse.getMessage());
			int i = 0;
			for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse
					.getSmsSendDetailDTOs()) {
				System.out.println("SmsSendDetailDTO[" + i + "]:");
				System.out.println("Content=" + smsSendDetailDTO.getContent());
				System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
				System.out.println("OutId=" + smsSendDetailDTO.getOutId());
				System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
				System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
				System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
				System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
				System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
			}
			System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
			System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
		}*/

	}
}
