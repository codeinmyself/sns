function checkInput(form){
    var inputs = form.getElementsByTagName('input');
    for(var i = 0;i<inputs.length;i++){
        if (inputs[i].style.backgroundColor=="#ff9966") {
            alert(inputs[i].style.backgroundColor);
            return false;
        }
    }
    alert("1");
    alert(inputs[0].style.backgroundColor);
    return true;
}
function checkDiv(form){
    var divs = form.getElementsByTagName('div');
    for(var i = 0;i<divs.length;i++){
        if (divs[i].innerHTML!="") {
            return false;
        };
    }
    return true;
}