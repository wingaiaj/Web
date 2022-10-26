function delFruit(fid){
    if(window.confirm("是否删除")){
        window.location.href='fruit.do?fid='+fid+'&operate=del';
    }
}
function pgDown(pageNo){
    window.location.href='fruit.do?pageNo='+pageNo;
}
function remain(){
    window.location.href='fruit.do?keyword='+"";

}