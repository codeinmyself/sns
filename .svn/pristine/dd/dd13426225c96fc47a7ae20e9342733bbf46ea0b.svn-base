$(document).ready(function () {
    (function () {
        var headerSize = document.getElementsByTagName('header')[0];
        var minWidth = 1070;
        new windowResize(headerSize,minWidth);
    }());
})
$(function(){
    $(".chosepic2").on("click",function(e){
        $("#updateFile").click();
        e.preventDefault();
    });
    $(".chosepic1").on("click",function(e){
        $("#addfile").click();
        e.preventDefault();
    });
});

$(function(){
    $('#wrap li').hover(function(){
        $('.text',this).stop().animate({
            height:'220px'
        });
    },function(){
        $('.text',this).stop().animate({
            height:'0'
        });
    });
});


function collection(name,description,showScope,id,picture){
    $(".modal").addClass("in");
    $(".modal").css('z-index','1001');
    $(".modal-backup").css('display','block');
    $("#collection_name").val(name);
    $("#collection_id").val(id);
    $("#previewImage").attr("src",picture);
    $("#collection_description").val(description);
    if(showScope=="01"){
        $('.show_scope1').attr('checked','checked');
    }
    if(showScope=="02"){
        $('.show_scope2').attr('checked','checked');
    }
    if(showScope=="03"){
        $('.show_scope3').attr('checked','checked');
    }
}
function addCollection(){
    $(".modal-add").addClass("in");
    $(".modal-add").css('z-index','1001');
    $(".modal-backup").css('display','block');
}

function  addNewGroup() {
    $(".modal-add").addClass("in");
    $(".modal-add").css('z-index','1001');
    $(".modal-backup").css('display','block');
}
function searchGroupName(name,id)
{
    $(".modal").addClass("in");
    $(".modal").css('z-index','1001');
    $(".modal-backup").css('display','block');
    $("#updateGroupId").val(id);
    $("#groupName").val(name);
}

$(function(){
    $(".close").on("click",function(){
        $(".modal").removeClass('in');
        $(".modal").css('z-index','-1');
        $(".modal-backup").css('display','none');
       // $(".modal-add").removeClass('in');
       // $(".modal-add").css('z-index','-1');
    });
    $(".add-close").on("click",function(){
        $(".modal-add").removeClass('in');
        $(".modal-add").css('z-index','-1');
        $(".modal-backup").css('display','none');

    });
})

/*
document.getElementById('file').onchange = function(evt) {

    // 如果浏览器不支持FileReader，则不处理

    if (!window.FileReader) return;

    var files = evt.target.files;

    for (var i = 0, f; f = files[i]; i++) {

        if (!f.type.match('image.*')) {

            continue;

        }


        var reader = new FileReader();

        reader.onload = (function(theFile) {

            return function(e) {

                // img 元素

                document.getElementById('previewImage').src = e.target.result;

            };

        })(f);


        reader.readAsDataURL(f);

    }

}*/
