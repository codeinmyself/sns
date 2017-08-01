function timeProcess (time) {
	var realTime = time.split('.')[0];
	return moment(realTime,"YYYY-MM-DD h:mm:ss").fromNow();
}