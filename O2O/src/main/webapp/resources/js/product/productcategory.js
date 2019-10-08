/**
 * 
 */
$(function() {
	getlist();
	function getlist(e) {
		$.ajax({
			url:"/O2O/shopadmin/getproductcategorylist",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					handleList(data.productCategoryList);
				}
			}
		});
	}
	
	function handleList(data) {
		var html = '';
		data.map(function(item,index) {
			html += '<div class ="row row-product-category now"><div class = "col-33 product-category-name">'
				+ item.productCategoryName +'</div><div class = "col-33">'
				+ item.priority
				+ '</div>'
				+ '<div class="col-33"><a href="#" class="button delete" data-id="' 
				+ item.productCategory
				+ '">删除</a></div></div>';
		});
		$('.category-wrap').html(html);
	}
	
	$('#add').click(function() {
		var tempHtml = '<div class = "row row-product-category temp">' 
			+ '<div class="col-33"><input class="category-input category" type="text" placeholder = "商品类名"></div>'
			+ '<div class="col-33"><input class="category-input priority" type="number" placeholder = "优先级"></div>'
			+ '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
			+ '</div>';
		$('.category-wrap').append(tempHtml);
	});
	
	$('#submit').click(function() {
		var tempArr = $('.temp');
		var productCategoryList = [];
		tempArr.map(function(index,item) {
			var tempObj = {};
			tempObj.productCategoryName = $(item).find('.category').val();
			tempObj.priority = $(item).find('.priority').val();
			if(tempObj.productCategoryName && tempObj.priority){
				productCategoryList.push(tempObj);
			}
		});
		$.ajax({
			url:'/O2O/shopadmin/addproductcategorys',
			type:'POST',
			data:JSON.stringify(productCategoryList),
			contentType:'application/json',
			success:function(data){
				if(data.success){
					alert("success");
					getlist();
				}else{
					alert("fail")
				}
			}
		});
	});
	
	$('.category-wrap').on('click','.row-product-category.temp .delete',
			function(e) {
				console.log($(this).parent().parent());
				$(this).parent().parent().remove();
			});
	$('.category-wrap').on('click','.row-product-category.now .delete',
			function(e) {
				var target = e.currentTarget;
					$.ajax({
						url:'/O2O/shopadmin/deleteproductcategory',
						type:'POST',
						data:{
							productCategoryId:target.dataset.id
						},
						dataType:'json',
						success:function(data){
							if(data.success){
								alert("success");
								getlist();								
							}else{
								alert("fail");								
							}
						}
					});
				
			});
	
})