define(function(require, exports, moudles) {
	require('jquery');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var template = require('arttemplate');
	var url = {
		api : baseRoot + "/admin/friendlink",
	};
	var $httpUtil = require('/js/common/httpUtil.js');
	var dialog = require('/js/common/dialog');
	var $msg= require('/js/common/alerts.js');
	window.dialog = dialog;

	  var postData = {
		      size: 10,
		      page:1,
		      sort:'',
		   }
		new Vue({
		        el: '#app',
		        data: postData,
		   });
	var laypage = require('laypage');
	var $tbody = $("#table-tbody");
    $("#btSelectAll").on("change",function() {
        var $checkBox = $tbody.find("input[name='id']:not(:disabled)");
        if ($(this).is(':checked')) {
            $checkBox.prop("checked",true);// 全选
        } else {
            $checkBox.prop("checked",false);// 反选
        }
    });
	// 修改事件
	$tbody.on("click", ".operate .btn-view",function() {
		var dataId = $(this).attr("data");
		var id = $(this).attr("id");
		$.httpUtil.curl({
			url : url.api  +"/"+dataId+".jhtml",
			loading : false,
			success : function(data) {
				dialog({
					id : id,
					title : "查看",
					cancelValue : '取消',
					bodyClass : "ui-dialog-body-min",
					height : "550",
					width : "450",
					content:data,
					onclose: function () {
						search(false);
					},
				}).showModal();
			}
		});
	 });
	var $add= $("#btn-add");
	$add.on("click", function() {
		var id = $(this).attr("id");
		var id = $(this).attr("id");
		$.httpUtil.curl({
			url : url.api  +"/add.jhtml",
			loading : false,
			success : function(data) {
				dialog({
					id : id,
					title : "添加子节点",
					cancelValue : '取消',
					bodyClass : "ui-dialog-body-min",
					height : "550",
					width : "450",
					content:data,
					onclose: function () {
						search(false);
					},
				}).showModal();
			}
		});
	});
	$(".page-size-sel").on("change", function() {
		$("#size").val($(this).val());
		$("#search-btn").click();
	});
	$("#refresh,#search-btn").on("click", function() {
		$("#page").val(1);
		search(true);
	});
	var search = function(loading) {
		$("#btSelectAll").prop("checked",false);
		var param = $("#search_form").serialize();
		$.httpUtil.curl({url : url.api + "s.json",
			type : "get",
			dataType : "json",
			loading : loading,
			data : param,
			success : function(data) {
				var html = template('tr-template-js', data.result);
				$("#pagination-total").html(data.result.totalElements);
				$tbody.empty();
				$tbody.append(html);
				var $pagination=$("#table-pagination");
				laypage({
					cont : $pagination, // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
					pages : data.result.totalPages, // 通过后台拿到的总页数
					curr : Number(data.result.number) + 1, // 初始化当前页
					skip : true,
					skin : '#AF0000',
					jump : function(obj, first) { // 触发分页后的回调
						if (!first) {
							var curr = obj.curr;
							$("#page").val(obj.curr);
							search(false);
						}
					}
				});
			}
		});
	};
	var $pagination=$("#table-pagination");
	laypage({
		cont : $pagination, // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
		pages : $pagination.attr('data-totalPages'), // 通过后台拿到的总页数
		curr : Number($pagination.attr('data-number')) + 1, // 初始化当前页
		skip : true,
		skin : '#AF0000',
		jump : function(obj, first) { // 触发分页后的回调
			if (!first) {
				var curr = obj.curr;
				$("#page").val(obj.curr);
				search(false);
			}
		}
	});
	
});