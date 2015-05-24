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
<!doctype html>
<html>
<head>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="/mgmt/toInnerHeaderPage.do" />
</head>

<body class="theme-blue inner-page">
	<div class="search-result">
		<div class="panel panel-default">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>岗位分类</th>
						<%-- <th>${gatherSrcNameList.get(0)}</th>
						<th>${gatherSrcNameList.get(1)}</th>
						<th>${gatherSrcNameList.get(2)}</th>
						<th>${gatherSrcNameList.get(3)}</th>
						<th>${gatherSrcNameList.get(4)}</th>
						<th>${gatherSrcNameList.get(5)}</th> --%>
						<th>58同城</th>
						<th>1010兼职网</th>
						<th>赶集网</th>
						<th>校园无忧</th>
						<th>百姓网</th>
						<th>兼职猫</th>
						<!-- <script type="text/javascript">
							function outputColumnList(gatherSrcNameList) {
								$("#skill_tbody").empty();
								for(var i in gatherSrcNameList){
									gatherSrcName = gatherSrcNameList(i);
									var thHtml = '<th>';
									thHtml += '<th>' + isUndefined(gatherSrcName) + ' ' + '</th>';
								}
							}
						</script> -->
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="skill_tbody"></tbody>
			</table>
		</div>
	</div>




	<div id="mpageDiv"></div>
	<script type="text/javascript">
		function initTableList(mapContentLists,jobClassifyList, gatherSrcIdList) {
			$("#skill_tbody").empty();
			
			/* for(var i in jobClassifyList){
				jobClassify = jobClassifyList[i];
				
				var trHtml = '<tr>';
				trHtml += '<td>' + isUndefined(jobClassify.jobClassifyName) + ' '
				+ '</td>';
				
				//gatherSourceTypeId == 1
				var flag = 0;
				for ( var listIndex in mapContentLists) {
					mapContent = mapContentLists[listIndex];
					
					var flag2 = 0;
					for( var j in gatherSrcIdList) {
						gatherSrcId = gatherSrcIdList(j);
						
						if(mapContent.jobClassifyId == jobClassify.id && mapContent.gatherSourceTypeId == gatherSrcId.getCode()){
							trHtml += '<td>' + isUndefined(mapContent.mappingContent.replace(/;;;/g, "<br/>")) + ' '
							+ '</td>';
							flag = 0;
							flag2 = 0;
							break;
						}
					}
					if(flag && flag2 == 0) {
						trHtml += '<td></td>';
					}
				} */
				
				
				for(var i in jobClassifyList){
					jobClassify = jobClassifyList[i];
					
					var trHtml = '<tr>';
					trHtml += '<td>' + isUndefined(jobClassify.jobClassifyName) + ' ' + '</td>';
					
					//gatherSourceTypeId == 1
					var flag = 0;
					for ( var j in gatherSrcIdList) {
						gatherSrcId = gatherSrcIdList[j];
						
						//var flag2 = 0;
						for( var listIndex in mapContentLists) {
							mapContent = mapContentLists[listIndex];
							
							if(mapContent.jobClassifyId == jobClassify.id && mapContent.gatherSourceTypeId == gatherSrcId){
								trHtml += '<td>' + isUndefined(mapContent.mappingContent.replace(/;;;/g, "<br/>")) + ' '
								+ '</td>';
								flag = 1;
								//flag2 = 1;
								break;
							}
						}
						if(flag == 0) {
							trHtml += '<td></td>';
						}
					}
					
					
				/* if(flag == 0){
					trHtml += '<td></td>';
				}
				 */
				//gatherSourceTypeId == 2
				/* var flag = 0;
				for ( var listIndex in mapContentLists) {
					mapContent = mapContentLists[listIndex];
					
					//var flag2 = 0;
					for( var j in gatherSrcIdList) {
						gatherSrcId = gatherSrcIdList(j);
						
						if(mapContent.jobClassifyId == jobClassify.id && mapContent.gatherSourceTypeId == gatherSrcId.getCode()){
							trHtml += '<td>' + isUndefined(mapContent.mappingContent.replace(/;;;/g, "<br/>")) + ' '
							+ '</td>';
							flag = 1;
							flag2 = 1;
							break;
						}						
					}
					if(flag== 0){
						trHtml += '<td></td>';
					}
				}
				
				//gatherSourceTypeId == 3
				var flag = 0;
				for ( var listIndex in mapContentLists) {
					mapContent = mapContentLists[listIndex];
					
					//var flag2 = 0;
					for(var j in gatherSrcIdList) {
						gatherSrcId = gatherSrcIdList(j);
						
						if(mapContent.jobClassifyId == jobClassify.id && mapContent.gatherSourceTypeId == 3){
							trHtml += '<td>' + isUndefined(mapContent.mappingContent.replace(/;;;/g, "<br/>")) + ' '
							+ '</td>';
							flag = 1;
							flag2 = 1;
							break;
						}
					}
					if(flag == 0) {
						trHtml += '<td></td>';
					}
				}
				
				//gatherSourceTypeId == 4
				var flag = 0;
				for ( var listIndex in mapContentLists) {
					mapContent = mapContentLists[listIndex];
					if(mapContent.jobClassifyId == jobClassify.id && mapContent.gatherSourceTypeId == 4){
						trHtml += '<td>' + isUndefined(mapContent.mappingContent.replace(/;;;/g, "<br/>")) + ' '
						+ '</td>';
						flag = 1;
						break;
					}						
				}
				if(flag == 0){
					trHtml += '<td></td>';
				}
			
				//gatherSourceTypeId == 5
				var flag = 0;
				for ( var listIndex in mapContentLists) {
					mapContent = mapContentLists[listIndex];
					if(mapContent.jobClassifyId == jobClassify.id && mapContent.gatherSourceTypeId == 5){
						trHtml += '<td>' + isUndefined(mapContent.mappingContent.replace(/;;;/g, "<br/>")) + ' '
						+ '</td>';
						flag = 1;
						break;
					}						
				}
				if(flag == 0){
					trHtml += '<td></td>';
				}
				//gatherSourceTypeId == 6
				var flag = 0;
				for ( var listIndex in mapContentLists) {
					mapContent = mapContentLists[listIndex];
					if(mapContent.jobClassifyId == jobClassify.id && mapContent.gatherSourceTypeId == 6){
						trHtml += '<td>' + isUndefined(mapContent.mappingContent.replace(/;;;/g, "<br/>")) + ' '
						+ '</td>';
						flag = 1;
						break;
					}						
				}
				if(flag == 0){
					trHtml += '<td></td>';
				} */
				
				trHtml += "<td><a class='update-skill' job_classify_id='"+ isUndefined(jobClassify.id)+ "' href='${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/toAlterGatherJobClassifyMappingPage.do?jobClassifyId="+ jobClassify.id+ "' data-toggle='modal' title='修改'>修改</a>";
				/* if(info.status==0){
					trHtml += "    <a id='status-show-skill' href='#myModal' data-to
					ggle='modal' status=1 data-position='"+info.id+ "'>显示</a></td>";
				}else{
					trHtml += "    <a id='status-hide-skill' href='#myModal' data-toggle='modal' status=0 data-position='"+info.id+ "'>隐藏</a> </td>";
				} */
				//trHtml += "    <a id='status-hide-skill' href='#myModal' data-toggle='modal' status=0 data-position=''>隐藏</a></td>";
				trHtml += '</tr>';
				$("#skill_tbody").append(trHtml);
			}
			
			
		}
		//alert('sss');

		var mpaging;
		/**
		 * 刷新界面
		 */
		function reflush() {
			if (mpaging) {
				mpaging.flush();
			}
		}

		$(document).ready(
			function() {
				mpaging = $("#mpageDiv").mpaging(
					{
						url : "${pageContext.request.contextPath}/mgmt/gather_job_classify_mapping/selectAll.do",
						getCountSize : function(result) {
							if (isExitsVariable(result.content.resultData.countSize)
									&& isExitsVariable(result.resultCode)
									&& result.resultCode > 0) {
								return result.content.resultData.countSize;
							} else {
								return 0;
							}
						},
						success : function(result) { // 请求服务端成功后的回调方法
							if (result) {
								// 组织数据 
								initTableList(result.content.resultData.mapContentLists,result.content.resultData.jobClassifyList,
										result.content.resultData.gatherSrcIdList);
							}
						},
					/**searchInfo : function() { // 获取搜索条件的方法
						return "uiLoginName=" + $.trim($("#uiLoginName").val()) + "&uiName=" + $.trim($("#uiName").val());
					}*/
					});
			});
	</script>
</body>
</html>
