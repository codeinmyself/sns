function windowResize(headerSize,minWidth) {
    this.minWidth = minWidth;
    this.headerSize = headerSize;
    this.initEvents();
}
windowResize.prototype={
    constructor:windowResize,
    initEvents:function () {
        var that = this;
        this.headerSize.style.width = window.screen.availWidth+'px';
        this.headerSize.style.width = document.body.clientWidth+'px';
        window.onresize=function (){
            if (document.body.clientWidth>that.minWidth)
            that.headerSize.style.width=document.body.clientWidth+'px';
            else
                that.headerSize.style.width=that.minWidth;
        }
    }
}