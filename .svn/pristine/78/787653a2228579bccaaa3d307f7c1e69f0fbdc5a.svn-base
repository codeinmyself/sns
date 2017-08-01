// JavaScript Document
function selectAll()
{
	var checkBoxs=document.getElementsByName("checkBoxs");
	var selectOrUnselect=false;
	for(var i=0;i<checkBoxs.length;i++)
	{
		if(checkBoxs[i].checked){
			selectOrUnselect=true;
			break;
		}
	}
	if(selectOrUnselect)
	{
		allUncheck(checkBoxs);
		}
	else 
	{
		allCheck(checkBoxs);
		}
}
function allCheck(checkBoxs)
{
	for(var i=0;i<checkBoxs.length;i++)
	{checkBoxs[i].checked=true;}
}
function allUncheck(checkBoxs)
{
	for(var i=0;i<checkBoxs.length;i++)
	{checkBoxs[i].checked=false;}
}

