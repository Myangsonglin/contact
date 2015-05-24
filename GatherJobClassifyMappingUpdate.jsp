<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath;
	if (request.getServerPort() == 80) {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + path + "/";
	} else {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">



<style>
div.theFirstRow {
	float: left;
	position: relative;
	margin-top: 25px;
	margin-bottom: 25px;
	position: relative;
}

div.firstForm {
	margin-bottom: 25px;
}

/*div.secondForm {
	float: rigth;
	position: relative;
	margin-top: 100px;
	margin-left: 450px;
	margin-bottom: 100px;
}*/
div.theSecondRow {
	float: left;
	position: relative;
	margin-top: 25px;
	margin-left: 200px;
	margin-bottom: 25px;
}

div.theThirdRow {
	float: left;
	positiion: relative;
	margin-top: 25px;
	margin-left: 200px;
	margin-bottom: 25px;
}

div.thirdForm {
	margin-bottom: 25px;
}
/*div.fourthForm {
	float: right;
	position: relative;
	margin-top: 80px;
	margin-left: 450px;
	margin-bottom: 100px;
}*/
div.fivethForm {
	margin-bottom: 25px;
}
</style>
</head>
<body class="theme-blue inner-page">
	<!-- <form id="gatherJobClassifyMappingForm" method="post" > -->
	<fieldset>
		<legend style="font-weight:bold; font-size:200%;">修改岗位分类映射</legend>

		<div class="panel panel-default">
			<fieldset>
				<legend style="font-weight:bold; font-size:150%;">岗位分类：${jobClassifyName}</legend>
				<input type="button" value="返回" onclick="returnHomepage()"
					style="width: 100px; height: 30px;"></input>

				<div id="mpageDiv">
				
				
				<c:forEach item="${list}" var="li">
					<div class="secondForm">
							<form id="tongcjobClassifyNametypeForm" 	method="post"	target="_self"
								action="${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do">
								<fieldset>
									<legend style="font-weight:bold;">
										${str_1010} <input type="hidden" name="gatherSourceId" value="2">
									</legend>
									<input type="hidden" name="jobClassifyId"
										value="${jobClassifyId }">

									<textarea name="classifyMapNameTxtArea" rows="8">
									${done_content1}</textarea>
									<input type="submit" value="保存" style="width: 50px;" />
								</fieldset>
							</form>
						</div>
				
				</c:forEach>
				
					<%-- <div class="theFirstRow">
						<div class="firstForm">
							<form id="tongcjobClassifyNametypeForm" method="post"
								target="_self"
								action="${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do">
								<fieldset>
									<legend style="font-weight:bold;">
										${str_58} <input type="hidden" name="gatherSourceId" value="1">
									</legend>
									<input type="hidden" name="jobClassifyId"
										value="${jobClassifyId }">
									<textarea name="classifyMapNameTxtArea" rows="8">${done_content0.replaceAll("/;;;/", "<br/>")}</textarea>
									<input type="submit" value="保存" style="width: 50px;" />
								</fieldset>
							</form>
						</div>

						<div class="secondForm">
							<form id="tongcjobClassifyNametypeForm" method="post"
								target="_self"
								action="${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do">
								<fieldset>
									<legend style="font-weight:bold;">
										${str_1010} <input type="hidden" name="gatherSourceId" value="2">
									</legend>
									<input type="hidden" name="jobClassifyId"
										value="${jobClassifyId }">

									<textarea name="classifyMapNameTxtArea" rows="8">${done_content1}</textarea>
									<input type="submit" value="保存" style="width: 50px;" />
								</fieldset>
							</form>
						</div>
					</div>

					<div class="theSecondRow">
						<div class="thirdForm">
							<form id="tongcjobClassifyNametypeForm" method="post"
								target="_self"
								action="${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do">
								<fieldset>
									<legend style="font-weight:bold;">
										${str_gj} <input type="hidden" name="gatherSourceId" value="3">
									</legend>
									<input type="hidden" name="jobClassifyId"
										value="${jobClassifyId }">

									<textarea name="classifyMapNameTxtArea" rows="8">${done_content2}</textarea>
									<input type="submit" value="保存" style="width: 50px;" />
								</fieldset>
							</form>
						</div>


						<div class="fourthForm">
							<form id="tongcjobClassifyNametypeForm" method="post"
								target="_self"
								action="${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do">
								<fieldset>
									<legend style="font-weight:bold;">
										${str_school} <input type="hidden" name="gatherSourceId" value="4">
									</legend>
									<input type="hidden" name="jobClassifyId"
										value="${jobClassifyId }">
									<textarea name="classifyMapNameTxtArea" rows="8">${done_content3}</textarea>
									<input type="submit" value="保存" style="width: 50px;" />
								</fieldset>
							</form>
						</div>
					</div>

					<div class="theThirdRow">
						<div class="fivethForm">
							<form id="tongcjobClassifyNametypeForm" method="post"
								target="_self"
								action="${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do">
								<fieldset>
									<legend style="font-weight:bold;">
										${str_bx} <input type="hidden" name="gatherSourceId" value="5">
									</legend>
									<input type="hidden" name="jobClassifyId"
										value="${jobClassifyId }">
									<textarea name="classifyMapNameTxtArea" rows="8">${done_content4}</textarea>
									<input type="submit" value="保存" style="width: 50px;" />
								</fieldset>
							</form>
						</div>

						<div class="sixthForm">
							<form id="tongcjobClassifyNametypeForm" method="post"
								target="_self"
								action="${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do">
								<fieldset>
									<legend style="font-weight:bold;">
										${str_jcat} <input type="hidden" name="gatherSourceId" value="6">
									</legend>
									<input type="hidden" name="jobClassifyId"
										value="${jobClassifyId }">
									<textarea name="classifyMapNameTxtArea" rows="8">${done_content5}</textarea>
									<input type="submit" value="保存" style="width: 50px;" />
								</fieldset>
							</form>
						</div>
					</div> --%>

				</div>
			</fieldset>
		</div>
	</fieldset>


	<div id="mpageDiv"></div>
	<script type="text/javascript">
		function returnHomepage() {
			//alert('click');
			//window.location.href='${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/list.jsp';
			window.history.back(-1);
		}

		/*
		 *提交添加岗位分类的表单
		 */
		/*$(document)
				.off('click', '.sub-insert-jobClassify')
				.on(
						'click',
						'.sub-insert-jobClassify',
						function() {
							var classifyMapNameTxtArea = $('.classifyMapNameTxtArea').val();
							var isCover = 0;
							formData = {
								classifyMapNameTxtArea: $.trim(classifyMapNameTxtArea),
								isCover : isCover
							};
							if (verify(classifyMapNameTxtArea)) {
								$.ajax({
										url : "${pageContext.request.contextPath}/mgmt/jobClassify/insertJobClassify.do",
										type : "post",
										data : formData,
										dataType : "json",
										success : function(resultData) {
											if (resultData.resultCode > 0) {
												$('#myModal').modal('hide');
												alert(resultData.resultMsg);
												reflush();
											} else if (resultData.resultCode == "jobClassify_exist") {
												if (confirm("岗位分类存在，是否覆盖？")) {
													isCover = 1;
													$.ajax({
															url : "${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertJobClassify.do",
															type : "post",
															data : {
																classifyMapNameTxtArea : $
																		.trim(classifyMapNameTxtArea),
															},
															dataType : "json",
															success : function(
																	resultData) {
																if (resultData.resultCode > 0) {
																	$('#myModal').modal('hide');
																	alert(resultData.resultMsg);
																	reflush();
																} else {
																	alert(resultData.resultMsg);
																}
															}
														});
												}
											} else {
												alert(resultData.resultMsg);
											}
										}
									});
							}
						});

		/*
		 *验证textarea表单提交的映射岗位分类名称
		 
		function verify(classifyMapNameTxtArea) {
			if (classifyMapNameTxtArea == '') {
				alert("请填写映射岗位分类名称");
				return false;
			}
			return true;

		}*/

		/*
		 *	修改岗位信息
		 */
		/**function update() {
				$.ajax({
					url : "${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/insertGatherJobClassifyMapping.do",
					type : "POST",
					data : $("#gatherJobClassifyMappingForm").serialize(),
					dataType : "json",
					success : function(resultData) {
						if (resultData.resultCode=="1") {
							alert(resultData.resultMsg);
							var url ="gather_job_classify_mapping/toGatherJobClassifyMappingPage.do";
							$('.return-handle').attr('data-url',url);
							$('a.return-handle').trigger('click');
						}else{
							alert(resultData.resultMsg);
						}
					}
				});		
		}*/
	</script>
</body>
</html>
