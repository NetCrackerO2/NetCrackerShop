// //удалить строку таблицы
// $('#productTable').on('click', '.delete', removeRow);
//
// function removeRow() {
//     $(this).closest('tr').remove();
// }

// //listener скрывает все оставляет категории
// $("#categoryRef").click(function () {
//     $('#categoryTable').css({'display': 'block'});
//     $('#productTable').css({'display': 'none'});
//     $('#clientsTable').css({'display': 'none'});
//     $('.addProductForm').css({'display': 'none'});
//     $('.addClientFormForm').css({'display': 'none'});
// });
//
// //listener скрывает все оставляет товары
// $("#goodsRef").click(function () {
//     $('#productTable').css({'display': 'block'});
//     $('#categoryTable').css({'display': 'none'});
//     $('#clientsTable').css({'display': 'none'});
//     $('.addClientForm').css({'display': 'none'});
//     $('.addCategoryFormForm').css({'display': 'none'});
// });
//
// //listener скрывает все оставляет клиентов
// $("#clientsRef").click(function () {
//     $('#clientsTable').css({'display': 'block'});
//     $('#productTable').css({'display': 'none'});
//     $('#categoryTable').css({'display': 'none'});
//     $('.addProductForm').css({'display': 'none'});
//     $('.addCategoryFormForm').css({'display': 'none'});
// });
//
// //удалить строку таблицы
// $('.editButton').on('click', function () {
//     var x=$(this).closest('tr'); //продукт
//     if(x.find('.editable').css('-webkit-user-modify')==='read-write')
//         x.find('.editable').css({'-webkit-user-modify':'read-only'});
//     else
//         x.find('.editable').css({'-webkit-user-modify':'read-write'});
// });
// //Добавление клиента, товара, категории
// //Клиент
// $('#clientsTable .add').on('click',function () {
//     $('#clientsTable').css({'display': 'none'});
//     $('.addClientForm').css({'display': 'block'});
// })
//
// $('.addClientForm .saveAdd').on('click',function () {
//     $('#clientsTable').css({'display': 'block'});
//     $('.addClientForm').css({'display': 'none'});
// })
//
// $('.addClientForm .cancel').on('click',function () {
//     $('#clientsTable').css({'display': 'block'});
//     $('.addClientForm').css({'display': 'none'});
// })
// //Категория
// $('#categoryTable .add').on('click',function () {
//     $('#categoryTable').css({'display': 'none'});
//     $('.addCategoryForm').css({'display': 'block'});
// })
//
// $('.addCategoryForm .saveAdd').on('click',function () {
//     $('#categoryTable').css({'display': 'block'});
//     $('.addCategoryForm').css({'display': 'none'});
// })
//
// $('.addCategoryForm .cancel').on('click',function () {
//     $('#categoryTable').css({'display': 'block'});
//     $('.addCategoryForm').css({'display': 'none'});
// })
// //Товар
// $('#productTable .add').on('click',function () {
//     $('#productTable').css({'display': 'none'});
//     $('.addProductForm').css({'display': 'block'});
// })
//
// $('.addProductForm .saveAdd').on('click',function () {
//     $('#productTable').css({'display': 'block'});
//     $('.addProductForm').css({'display': 'none'});
// })
//
// $('.addProductForm .cancel').on('click',function () {
//     $('#productTable').css({'display': 'block'});
//     $('.addProductForm').css({'display': 'none'});
// })
// var app = angular.module('app', []);

// app.controller('Ctrl', ['$scope',
//     function ($scope) {
//         $('#order_info_Modal).on('show.bs.modal', function (event) {
//             var button = $(event.relatedTarget);
//             $scope.$apply(function () {
//                 var order_id = button.data('order-id');
//                 $scope.test = order_id;
//                 $( "body" ).data( "1", order_id );
//             });
//         });
//     }
// ]);

function onResponse(xhr, onFinish) {
    if (xhr.readyState != 4) return;
    var container = document.getElementsByClassName('error')[0];
    var el = document.getElementById('error_msg');
    while (el.firstChild) {
        el.removeChild(el.firstChild);
    }
    container.style.display="none";
    if (xhr.status != 200) {
        el.appendChild(document.createTextNode(xhr.status + ': ' + xhr.statusText));
        container.style.display="block";
    } else {
        el.appendChild(document.createTextNode(xhr.responseText));
        if(xhr.responseText.length>0)
            container.style.display="block";
        else
            onFinish();
    }
};

//Изменение товара
$('.editProductButton').on('click', function () {
    var x = $(this).closest('tr');
    if (this.value == 'Изменить') {
        $(this).val("Сохранить");
        //x.find('.editable').css({'-webkit-user-modify': 'read-write', 'background': 'wheat'});
        x.find('.editable').css({'background': 'wheat'});
        x.find('.contenteditable').attr('contenteditable','true');
        x.find('select.category').prop("disabled", false);
    } else {
        if (this.value == 'Сохранить') {
            var btn = $(this);
            var onFinish = function() {
                btn.val("Изменить");
               // x.find('.editable').css({'-webkit-user-modify': 'read-only','background': 'none'});
                x.find('.editable').css({'background': 'none'});
                x.find('.contenteditable').attr('contenteditable','false');
                x.find('select.category').prop("disabled", true);
            }
            try {
                var id = x.find('.id').html().trim();
                var name = x.find('.name').html().replace(/<br>/g,'').trim();
                var description = x.find('.description').html().replace(/<br>/g,'').trim();
                var count = x.find('.count').html().replace(/<br>/g,'').trim();
                var price = x.find('.price').html().replace(/<br>/g,'').trim();
                var select = x.find('select.category');
                var category=select[0].options[select[0].selectedIndex].value;
                request = new XMLHttpRequest();
                request.onreadystatechange = function(){
                    onResponse(request, onFinish);
                };
                var param = "&productId=" + id + "&productName=" + name + "&productDescription=" + description + "&productCount=" + count + "&productPrice="
                    + price+"&productCategory="+category;
                request.open('GET', '/productsServlet.jsp?editProduct=ok' + param, true);
                request.send(null);
            }
            catch (exception) {
                alert("Request failed");
            }
        }
    }
})
    //Изменение категории
    $('.editCategoryButton').on('click', function () {
        var x = $(this).closest('tr');
        if (this.value == 'Изменить') {
            $(this).val("Сохранить");
            //x.find('.editable').css({'-webkit-user-modify': 'read-write', 'background': 'wheat'});
            x.find('.editable').css({'background': 'wheat'});
            x.find('.contenteditable').attr('contenteditable','true');
        } else {
            if (this.value == 'Сохранить') {
                var btn = $(this);
                var onFinish = function() {
                    btn.val("Изменить");
                    //x.find('.editable').css({'-webkit-user-modify': 'read-only','background': 'none'});
                    x.find('.editable').css({'background': 'none'});
                    x.find('.editable').attr('contenteditable','false');
                }
                try {
                    var id = x.find('.id').html().trim();
                    var name = x.find('.name').html().replace(/<br>/g,'').trim();
                    request = new XMLHttpRequest();
                    request.onreadystatechange = function(){
                        onResponse(request, onFinish);
                    };
                    var param = "&categoryId=" + id + "&categoryName=" + name;
                    request.open('GET', '/categoriesServlet.jsp?editCategory=ok' + param, true);
                    request.send(null);
                }
                catch (exception) {
                    alert("Request failed");
                }
            }
        }
    })
//Изменение клиента
    $('.editClientButton').on('click', function () {
        var x = $(this).closest('tr');
        if (this.value == 'Изменить') {
            $(this).val("Сохранить");
            //x.find('.editable').css({'-webkit-user-modify': 'read-write','background': 'wheat'});
            x.find('.editable').css({'background': 'wheat'});
            x.find('.contenteditable').attr('contenteditable','true');
            x.find('select.isAdmin').prop("disabled", false);

        } else {
            if (this.value == 'Сохранить') {
                var btn = $(this);
                var onFinish = function() {
                    btn.val("Изменить");
                    //x.find('.editable').css({'-webkit-user-modify': 'read-only','background': 'none'});
                    x.find('.editable').css({'background': 'none'});
                    x.find('.contenteditable').attr('contenteditable','false');;
                    x.find('select.isAdmin').prop("disabled", true);
                };
                try {
                    var id = x.find('.id').html().trim();
                    var name = x.find('.name').html().replace(/<br>/g,'').trim();
                    var defaultAddress = x.find('.defaultAddress').html().replace(/<br>/g,'').trim();
                    var select = x.find('select.isAdmin');
                    var isAdmin=select[0].options[select[0].selectedIndex].value;
                    request = new XMLHttpRequest();
                    request.onreadystatechange = function(){
                        onResponse(request, onFinish);
                    };
                    var param = "&clientId=" + id + "&clientName=" + name + "&clientDefaultAddress=" + defaultAddress
                        +"&isAdmin="+isAdmin;
                    request.open('GET', '/clientsServlet.jsp?editClient=ok' + param, true);
                    request.send(null);
                }
                catch (exception) {
                    alert("Request failed");
                }
            }
        }
    })
