package com.wodan.shijianke.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.lang.StringUtils;

//import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
//import com.wodan.platform.foundation.exception.BusinessException;
import com.wodan.shijianke.account.model.SessionBean;
import com.wodan.shijianke.commom.web.PageQueryBean;
import com.wodan.shijianke.commom.web.PageQueryUtils;
import com.wodan.shijianke.constant.ShijiankeMgrConstants;
import com.wodan.shijianke.constant.ShijiankeMgrSessionComstants;
import com.wodan.shijianke.controller.base.AbstractBaseController;
import com.wodan.shijianke.controller.base.AbstractBaseController.CallbackHandler;
import com.wodan.shijianke.entity.GatherJobClassifyMappingBean;
import com.wodan.shijianke.entity.JobClassifyBean;
import com.wodan.shijianke.enums.GatherJobSourceEnum;
import com.wodan.shijianke.enums.JobClassifyEnum;
import com.wodan.shijianke.enums.SkillStatusEnum;
import com.wodan.shijianke.model.GatherJobClassifyMapping;
import com.wodan.shijianke.model.JobClassify;
import com.wodan.shijianke.model.Skill;
import com.wodan.shijianke.service.GatherJobClassifyMappingService;
import com.wodan.shijianke.service.JobClassifyService;
import com.wodan.shijianke.util.ActionLogUtils;
import com.wodan.shijianke.exception.BusinessException;

/**
 * @ClassName: GatherJobClassifyMappingController
 * @Description: 岗位分类控制器
 * @author yangb
 * @date 2015-4-30 下午17:11
 * @history
 * 
 */
@Controller
@RequestMapping(value = "/mgmt/gather_job_classify_mapping/")
public class GatherJobClassifyMappingController extends AbstractBaseController {

	@Autowired
	private GatherJobClassifyMappingService gatherJobClassifyMappingService;

	@Autowired
	private JobClassifyService jobClassifyService;

	/**
	 * 跳转到采集数据映射列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toGatherJobClassifyMappingPage")
	public ModelAndView toGatherJobClassifyMappingPage(
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// List<GatherJobClassifyMapping> classifyMapping =
		// gatherJobClassifyMappingService.selectAll();
		// modelMap.put("classifyMapping", classifyMapping);

		// List<JobClassify> jobClassifyName =
		// jobClassifyService.getClassfyName(1L);
		// modelMap.put("jobClassifyMapping", jobClassifyName);

		// GatherJobClassifyMappingBean gjcmb =
		// gatherJobClassifyMappingService.getMappingContent();
		// String map_cont = gjcmb.getMappingContent();
		// modelMap.put("map_cont", map_cont);

		return new ModelAndView("/mgmt/gather_job_classify_mapping/list.jsp",
				modelMap);
	}

	/**
	 * 跳转到修改数据映射列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toAlterGatherJobClassifyMappingPage")
	// @ResponseBody
	public ModelAndView toAlterJobClassifyMapping(HttpServletRequest request,
			@RequestParam Map<String, String> formParams) {
		String jobClassifyId = formParams.get("jobClassifyId");
		Long cjobClassifyId = Long.parseLong(jobClassifyId);

		GatherJobClassifyMappingBean gjcmb = gatherJobClassifyMappingService.selectById(cjobClassifyId);
		String jobClassifyName = gjcmb.getJobClassifyName();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("jobClassifyId", jobClassifyId);
		modelMap.put("jobClassifyName", jobClassifyName);
		
		
		// 从 com.wodan.shijianke.enums.GatherJobSourceEnum 中获得采集来源名称
		String str_58 =  GatherJobSourceEnum.SOURCE_58.getDesc();
		String str_1010 =  GatherJobSourceEnum.SOURCE_1010.getDesc();
		String str_gj =  GatherJobSourceEnum.GANJI.getDesc();
		String str_school =  GatherJobSourceEnum.SCHOOL51.getDesc();
		String str_bx =  GatherJobSourceEnum.BAIXING.getDesc();
		String str_jcat =  GatherJobSourceEnum.JIANZHIMAO.getDesc();
		modelMap.put("str_58", str_58);
		modelMap.put("str_1010", str_1010);
		modelMap.put("str_gj", str_gj);
		modelMap.put("str_school", str_school);
		modelMap.put("str_bx", str_bx);
		modelMap.put("str_jcat", str_jcat);

		// 把mappingContent的内容放到textarea作为默认值
		List<GatherJobClassifyMappingBean> gjcmblst = gatherJobClassifyMappingService.getMappingContent();
		// if(gjcmblst.size() < 0) {
		// modelMap.put(null, null);
		// }
		if (gjcmblst.size() < 1) {
			// for(GatherJobClassifyMappingBean gjcmb2 : gjcmblst){
			// String done_content = gjcmb2.getMappingContent();
			// modelMap.put("done_content", done_content);
			// }
			modelMap.put("hello", null);
			// String done_content0 = gjcmblst.get(0).getMappingContent();
			// modelMap.put("done_content0", done_content0);

			// for(int i=0; i<6; i++) {
			// String done_content = gjcmblst.get(i).getMappingContent(); }
		} else if (gjcmblst.size() < 2) {
			String done_content0 = gjcmblst.get(0).getMappingContent();
			modelMap.put("done_content0", done_content0);
		} else if (gjcmblst.size() < 3) {
			String done_content0 = gjcmblst.get(0).getMappingContent();
			modelMap.put("done_content0", done_content0);
			String done_content1 = gjcmblst.get(1).getMappingContent();
			modelMap.put("done_content1", done_content1);
		} else if (gjcmblst.size() < 4) {
			String done_content0 = gjcmblst.get(0).getMappingContent();
			modelMap.put("done_content0", done_content0);
			String done_content1 = gjcmblst.get(1).getMappingContent();
			modelMap.put("done_content1", done_content1);
			String done_content2 = gjcmblst.get(2).getMappingContent();
			modelMap.put("done_content2", done_content2);
		} else if (gjcmblst.size() < 5) {
			String done_content0 = gjcmblst.get(0).getMappingContent();
			modelMap.put("done_content0", done_content0);
			String done_content1 = gjcmblst.get(1).getMappingContent();
			modelMap.put("done_content1", done_content1);
			String done_content2 = gjcmblst.get(2).getMappingContent();
			modelMap.put("done_content2", done_content2);
			String done_content3 = gjcmblst.get(3).getMappingContent();
			modelMap.put("done_content3", done_content3);
		} else if (gjcmblst.size() < 6) {
			String done_content0 = gjcmblst.get(0).getMappingContent();
			modelMap.put("done_content0", done_content0);
			String done_content1 = gjcmblst.get(1).getMappingContent();
			modelMap.put("done_content1", done_content1);
			String done_content2 = gjcmblst.get(2).getMappingContent();
			modelMap.put("done_content2", done_content2);
			String done_content3 = gjcmblst.get(3).getMappingContent();
			modelMap.put("done_content3", done_content3);
			String done_content4 = gjcmblst.get(4).getMappingContent();
			modelMap.put("done_content4", done_content4);
		} else if  (gjcmblst.size() < 7) {
			String done_content0 = gjcmblst.get(0).getMappingContent();
			modelMap.put("done_content0", done_content0);
			String done_content1 = gjcmblst.get(1).getMappingContent();
			modelMap.put("done_content1", done_content1);
			String done_content2 = gjcmblst.get(2).getMappingContent();
			modelMap.put("done_content2", done_content2);
			String done_content3 = gjcmblst.get(3).getMappingContent();
			modelMap.put("done_content3", done_content3);
			String done_content4 = gjcmblst.get(4).getMappingContent();
			modelMap.put("done_content4", done_content4);
			String done_content5 = gjcmblst.get(5).getMappingContent();
			modelMap.put("done_content5", done_content5);
		}else {
			String done_content0 = gjcmblst.get(0).getMappingContent();
			modelMap.put("done_content0", done_content0);
			String done_content1 = gjcmblst.get(1).getMappingContent();
			modelMap.put("done_content1", done_content1);
			String done_content2 = gjcmblst.get(2).getMappingContent();
			modelMap.put("done_content2", done_content2);
			String done_content3 = gjcmblst.get(3).getMappingContent();
			modelMap.put("done_content3", done_content3);
			String done_content4 = gjcmblst.get(4).getMappingContent();
			modelMap.put("done_content4", done_content4);
			String done_content5 = gjcmblst.get(5).getMappingContent();
			modelMap.put("done_content5", done_content5);
		}

		// List<String> mappingContentlst = gjcmblst.getMappingContent();
		// modelMap.put("mappingContentlst", done_content);
		
		JobClassifyMapBean jobClassifyMapBean = new JobClassifyMapBean();

		return new ModelAndView("/mgmt/gather_job_classify_mapping/update.jsp",
				modelMap);
	}

	/**
	 * 采集数据映射查询
	 * 
	 * @param request
	 * @param response
	 * @param formParams
	 * @return
	 */
	@RequestMapping(value = "selectAll")
	@ResponseBody
	public String selectAll(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, String> formParams) {
		return executeJson(request, response, session, formParams, new CallbackHandler() {
					@Override
					public JSONObject doInCallBack(HttpServletRequest request,
							HttpServletResponse response, HttpSession session, Map<String, String> formParams) {

//						Map<String, Object> queryskillMap = new
//						HashMap<String, Object>();
//						Map<String, Object> countMap = new HashMap<String, Object>();// 查询记录的map结合
//						分页封装
//						PageQueryBean pageQueryBean =
//						PageQueryUtils.parsePageQueryBean(formParams,10);
//
//						queryskillMap.putAll(formParams);

						List<GatherJobClassifyMappingBean> mapContentList = gatherJobClassifyMappingService.selectAll();
						// List<Skill> skillLists = new LinkedList<Skill>();
						// 状态的转换
						/**
						 * for(GatherJobClassifyMapping skill : skillList){
						 * skill.setStatusValue(SkillStatusEnum.strNormaL(skill.
						 * getStatus())); skillLists.addAll(skill); }
						 */

						Map<String, Object> queryJobClassifyMap = new HashMap<String, Object>();
						
						
						
						// 分页封装
						List<JobClassifyBean> jobClassifyList = jobClassifyService.selectJobClassifyPage(queryJobClassifyMap, 1, 1000);

						Map<String, Object> resultData = new HashMap<String, Object>();
						
						// 从 com.wodan.shijianke.enums.GatherJobSourceEnum 中获得采集来源名称
//						String str_58 =  GatherJobSourceEnum.SOURCE_58.getDesc();
//						String str_1010 =  GatherJobSourceEnum.SOURCE_1010.getDesc();
//						String str_gj =  GatherJobSourceEnum.GANJI.getDesc();
//						String str_school =  GatherJobSourceEnum.SCHOOL51.getDesc();
//						String str_bx =  GatherJobSourceEnum.BAIXING.getDesc();
//						String str_jcat =  GatherJobSourceEnum.JIANZHIMAO.getDesc();
//						resultData.put("str_58", str_58);
//						resultData.put("str_1010", str_1010);
//						resultData.put("str_gj", str_gj);
//						resultData.put("str_school", str_school);
//						resultData.put("str_bx", str_bx);
//						resultData.put("str_jcat", str_jcat);
						
//						List<Object> sf = new LinkedList<Object>();
						List<Object> gatherSrcIdList = new LinkedList<Object>();
						for (GatherJobSourceEnum gatherJobSourceEnum : GatherJobSourceEnum.values()) {
							//Map<String, Object> gatherJobs = new HashMap<String, Object>();
							int gatherSrcId = gatherJobSourceEnum.getCode();
							//resultData.put("gatherSrcNameList", gatherJobSourceEnum.getDesc());
							//sf.add(gatherJobs);
							gatherSrcIdList.add(gatherSrcId);
							resultData.put("gatherSrcIdList", gatherSrcIdList);
						}
						
						
						
						resultData.put("mapContentLists", mapContentList);
						resultData.put("jobClassifyList", jobClassifyList);
						// resultData.put("countSize",
						// gatherJobClassifyMappingService.getSkillCounts(countMap));//获取总记录数

						JSONObject js = new JSONObject();
						js.put("resultData", resultData);
						return js;
					}
				});

	}

	/**
	 * 添加岗位分类和各大网站采集的岗位分类数据的映射关系
	 * 
	 * @param request
	 * @param response
	 * @param formParams
	 * @return
	 */
	@RequestMapping(value = "insertGatherJobClassifyMapping")
	// @ResponseBody
	public ModelAndView insertMapping(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, String> formParams) {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// return executeJson(request, response,session, formParams, new
		// CallbackHandler(){
		// @Override
		// public JSONObject doInCallBack(HttpServletRequest request,
		// HttpServletResponse response,HttpSession session,
		// Map<String, String> formParams) {

		GatherJobClassifyMappingBean gjcmb = new GatherJobClassifyMappingBean();

		String jobClassifyName = formParams.get("jobClassifyName");

		// Long id = lng[] //主键id不用填入数据库
		String jobClassifyId = formParams.get("jobClassifyId");
		Long cjobClassifyId = Long.parseLong(jobClassifyId);
		
		// Long gatherSourceId = 5L;
		// GatherJobSourceEnum gatherSourceTypeId =
		// GatherJobSourceEnum.SOURCE_1010;
		// Long cgatherSourceTypeId = Long.parseLong(gatherSourceTypeId);
		// int gath = gatherSourceTypeId.getCode();
		// Long cgath = (long)gath;
		

		String gatherSrcId = formParams.get("gatherSourceId");
		Long cgatherSrcId = Long.parseLong(gatherSrcId);
		String clasfyMapName = formParams.get("classifyMapNameTxtArea");
		if (gatherSrcId != null) {
			gjcmb.setGatherSourceTypeId(Long.parseLong(gatherSrcId));
			gjcmb.setMappingContent(clasfyMapName.replaceAll("\r\n", ";;;"));
		}

		gjcmb.setJobClassifyId(cjobClassifyId);
		gjcmb.setUpdateTime(new Date());

		// String isCover = formParams.get("isCover").trim();
		// if( StringUtils.isBlank(jobClassifyName)){
		// throw new
		// BusinessException(ShijiankeMgrConstants.ERROR_JOB_CLASSIFY_ID,ShijiankeMgrConstants.descMap.get(ShijiankeMgrConstants.ERROR_JOB_CLASSIFY_ID));
		// }

		// SessionBean userinfo =
		// (SessionBean)session.getAttribute(ShijiankeMgrSessionComstants.SESSIONUSERINFO);
		// gjcmb.setCreateId(userinfo.getUserInfoId());

		// String content = "添加排序为："+sort+",技能名称为："+skillName;
		// String failReason = "";

		// 判断岗位分类名称是否已经存在
		Integer hasSameName = 0;
		List<GatherJobClassifyMappingBean> mappingList = gatherJobClassifyMappingService
				.selectAll();
		if (mappingList.size() > 0) {
			for (GatherJobClassifyMappingBean mapping : mappingList) {
				if (mapping.getGatherSourceTypeId() != null
						&& mapping.getJobClassifyId() != null) {
					if (mapping.getJobClassifyId().equals(cjobClassifyId)
							&& mapping.getGatherSourceTypeId().equals(
									cgatherSrcId)) {
						gjcmb.setId(mapping.getId());
						hasSameName = 1;
						break;
						// if(isCover.equals("0"))
						// throw new BusinessException("skill_exist","技能已经存在");
					}
				}
			}
		}

		Integer excuteResult = 0;
		// isCover.equals("1") &&
		if (hasSameName == 1) {
			gatherJobClassifyMappingService.updateMapping(gjcmb);
		} else {
			gatherJobClassifyMappingService.insertMapping(gjcmb);
		}
		// if(excuteResult<1){
		// failReason= "添加失败";
		// ActionLogUtils.insertAdminLog(request, session, content, failReason,
		// new Date());
		// throw new
		// BusinessException(ShijiankeMgrConstants.ERROR,ShijiankeMgrConstants.descMap.get(ShijiankeMgrConstants.ERROR));
		// }
		// ActionLogUtils.insertAdminLog(request, session, content, failReason,
		// new Date());


		// 把mappingContent的内容放到list.jsp
		List<GatherJobClassifyMappingBean> map_contList = gatherJobClassifyMappingService
				.getMappingContent();
		// String mappingContent = gjcmb.getMappingContent();
		modelMap.put("map_contList", map_contList);

		return new ModelAndView("/mgmt/gather_job_classify_mapping/list.jsp",
				modelMap);

	}
}

/**
 * 查询岗位分类信息
 */
/**
 * @RequestMapping(value="selectJobClassifyAll")
 * @ResponseBody public String selectJobClassifyAll(HttpServletRequest
 *               request,HttpServletResponse response,HttpSession
 *               session,@RequestParam Map<String, String> formParams){ return
 *               executeJson(request, response,session, formParams, new
 *               CallbackHandler(){
 * @Override public JSONObject doInCallBack(HttpServletRequest request,
 *           HttpServletResponse response,HttpSession session, Map<String,
 *           String> formParams) { Map<String, Object> queryJobClassifyMap = new
 *           HashMap<String, Object>(); Map<String, Object> countMap = new
 *           HashMap<String, Object>();//查询记录的map结合 //分页封装 PageQueryBean
 *           pageQueryBean = PageQueryUtils.parsePageQueryBean(formParams,10);
 *           queryJobClassifyMap.putAll(formParams);
 * 
 *           List<JobClassifyBean> jobClassifyList =
 *           gatherJobClassifyMappingService
 *           .selectJobClassifyPage(queryJobClassifyMap
 *           ,pageQueryBean.getCurrentPage(),pageQueryBean.getPageSize());
 * 
 *           Map<String, Object> resultData = new HashMap<String, Object>();
 *           resultData.put("jobClassifyList", jobClassifyList);
 *           resultData.put("countSize",
 *           gatherJobClassifyMappingService.getClassfyCounts
 *           (countMap));//获取总记录数
 * 
 *           JSONObject js = new JSONObject(); js.put("resultData", resultData);
 *           return js; } }); }
 */

/**
 * 修改岗位分类信息
 * 
 * @param request
 * @param response
 * @param formParams
 * @return
 */

/**
 * @RequestMapping(value = "updateGatherJobClassifyMapping")
 * @ResponseBody public String updateGatherJobClassifyMapping(HttpServletRequest
 *               request,HttpServletResponse response,HttpSession session,
 * @RequestParam Map<String, String> formParams){ return executeJson(request,
 *               response,session, formParams, new CallbackHandler(){
 * @Override public JSONObject doInCallBack(HttpServletRequest request,
 *           HttpServletResponse response,HttpSession session, Map<String,
 *           String> formParams) {
 * 
 *           GatherJobClassifyMappingBean jcb = new
 *           GatherJobClassifyMappingBean();
 * 
 *           String id = formParams.get("id").trim(); String jobClassifyId =
 *           formParams.get("job_classify_id").trim(); //String
 *           job_classify_name = formParams.get("job_classify_name").trim();
 *           String gatherSourceTypeId =
 *           formParams.get("gather_source_type_id").trim(); String
 *           mappingContent = formParams.get("mapping_content").trim(); String
 *           updateTime = formParams.get("update_time").trim();
 * 
 *           /**if(StringUtils.isBlank(jobClassifyId)){ throw new
 *           BusinessException
 *           (ShijiankeMgrConstants.ERROR_JOBCLASSFY_NAME,ShijiankeMgrConstants
 *           .descMap.get(ShijiankeMgrConstants.ERROR_JOBCLASSFY_NAME)); }
 *           if(StringUtils.isBlank(gatherSourceTypeId)){ throw new
 *           BusinessException
 *           (ShijiankeMgrConstants.ERROR_JOBCLASSFY_HOT,ShijiankeMgrConstants
 *           .descMap.get(ShijiankeMgrConstants.ERROR_JOBCLASSFY_HOT)); }
 *           if(StringUtils.isBlank(mappingContent)){ throw new
 *           BusinessException(ShijiankeMgrConstants.ERROR_JOBCLASSFY_ORDER,
 *           ShijiankeMgrConstants
 *           .descMap.get(ShijiankeMgrConstants.ERROR_JOBCLASSFY_ORDER)); }
 * 
 *           jcb.setId(Integer.parseInt(id));
 *           jcb.setJobClassifyId(Interger.parseInt(jobClassifyId));
 *           jcb.setGatherSourceTypeId(Integer.parseInt(gatherSourceTypeId));
 *           jcb.setMappingContent(mappingContent);
 *           jcb.setUpdateTime(Integer.parseInt(updateTime));
 * 
 *           // 判断所选区域下是否存在同样名称的学校 /**Map<String,Object> jobClassifyMap=new
 *           HashMap<String,Object>(); List<JobClassifyBean>
 *           jobClassifyList=gatherJobClassifyMappingService
 *           .selectJobClassify(jobClassifyMap); if(jobClassifyList.size()>0){
 *           for(JobClassifyBean jobClassifyBean:jobClassifyList){
 *           if(jobClassifyBean
 *           .getJobClassifyName().equals(jobClassifyName)&&!jobClassifyBean
 *           .getId().equals(Long.parseLong(id))){ throw new
 *           BusinessException("(jobClassify_exist","岗位分类已存在"); } } }
 * 
 *           String isHot = ""; if(Integer.parseInt(hotJobClassify) ==
 *           YesNoEnum.YES.getCode()){ isHot = "热门"; }else{ isHot = "非热门"; }
 *           String content =
 *           "修改岗位分类，岗位名称为："+jobClassifyName+"，岗位热门情况为:"+isHot+"，岗位分类备注为："
 *           +desc+"，岗位排序为："+order; String failReason = ""; Integer excuteResult
 *           = gatherJobClassifyMappingService.updateGatherJobClassifyMapping(
 *           jcb); //jobClassifierCache.deleteAll(); if(excuteResult<1){
 *           failReason = "操作失败"; ActionLogUtils.insertAdminLog(request,
 *           session, content, failReason, new Date()); throw new
 *           BusinessException
 *           (ShijiankeMgrConstants.ERROR,ShijiankeMgrConstants.
 *           descMap.get(ShijiankeMgrConstants.ERROR)); }
 *           ActionLogUtils.insertAdminLog(request, session, content,
 *           failReason, new Date());
 * 
 *           return null; } });
 * 
 *           }
 */
