//JQUERY
$(function () {
	//从url中解析shopId值
	var shopId = getQueryString("shopId");
	//判断是否传入了shopId，传入了说明是调用按ID查询，若没有传入说明是注册
	var isEdit = shopId?true:false;
	//初始化表单信息，主要是查询数据库，填入区域等下拉菜单
	var initUrl = '/O2O/shopadmin/getshopinitinfo';
	//注册店铺页面
	var registerShopUrl = '/O2O/shopadmin/registershop';
	//查询店铺页面
	var shopInfoUrl = "/O2O/shopadmin/getshopbyid?shopId=" + shopId;
	//修改店铺页面
	var editShopUrl = "/O2O/shopadmin/modifyshop";
	//没有id调用初始化方法（注册）,有ID修改
	if(!isEdit){
		getShopInitInfo();		
	}else{
		getShopInfo(shopId);
	}
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if(data.success){
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'
					+ shop.shopCategory.shopCategoryId + '"selected>'
					+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = "";
				data.areaList.map(function(item,index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
					+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				//类别不允许修改
				$('#shop-category').attr("disabled",'disabled');
				$('#area').html(tempAreaHtml);
				$("#area option[data-id = '" + shop.area.areaId +"']").attr('selected','selected');
			}
		});
	}
	
	function getShopInitInfo() {
		$.getJSON(initUrl,function(data){
			if (data.success) {
				//将数据库中查的的数据拼接成html，返回前端下拉列表
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item,index) {
					tempHtml += '<option data-id="' + item.shopCategoryId + '">' 
					+ item.shopCategoryName + '</option>';	
				});
				data.areaList.map(function(item,index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">' 
					+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
		$('#submit').click(function() {
			var shop = {};
			if(isEdit){
				shop.shopId = shopId;
			}
			shop.shopName = $('#shop-name').val();
			shop.shopAddr = $('#shop-addr').val();
			shop.phone = $('#shop-phone').val();
			shop.shopDesc = $('#shop-desc').val();
			shop.shopCategory = {
					shopCategoryId:$('#shop-category').find('option').not(function() {
						return !this.selected;
					}).data('id')
			};
			shop.area = {
					areaId:$('#area').find('option').not(function() {
						return !this.selected;
					}).data('id')
			};
			var shopImg = $('#shop-img')[0].files[0];
			var formData = new FormData();
			formData.append('shopImg',shopImg);
			formData.append('shopStr',JSON.stringify(shop));
			var verifyCodeActual = $('#j_captcha').val();
			if(!verifyCodeActual){
				alert('请输入验证码');
				return;
			}
			formData.append('verifyCodeActual',verifyCodeActual);
			$.ajax({
				url:(isEdit?editShopUrl:registerShopUrl),
				type:'POST',
				data:formData,
				contentType:false,
				processData:false,
				cache:false,
				success:function(data){
					if(data.success){
						alert('success!');
					}else {
						alert('fails:'+data.errMsg);
					}
				}
			});
		});
})
