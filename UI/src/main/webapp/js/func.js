
//удалить строку таблицы
		  $('#goodsTable').on('click', '.delete', removeRow);

		  function removeRow() {
		    $(this).closest('tr').remove();
		  }

		  //listener скрывает товары оставляет категории
		  $("#categoryRef").click(function () {
		    $('#categoryForm').css({'display':'block'});
		     $('#goodsForm').css({'display':'none'});
			});

		   //listener скрывает товары оставляет категории
		  $("#goodsRef").click(function () {
		    $('#categoryForm').css({'display':'none'});
		     $('#goodsForm').css({'display':'block'});
			});



