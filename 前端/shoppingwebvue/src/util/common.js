const commentUtil = {
    //计算日期之间天数
    DateDiff(sDate, eDate) { //sDate和eDate是yyyy-MM-dd格式
        var date1 = new Date(sDate);
        var date2 = new Date(eDate);
        var date3 = date2.getTime() - date1.getTime();
        var days = Math.floor(date3 / (24 * 3600 * 1000));
        return days;
    },
    //获取当前日期
    getCurrentDate() { //sDate和eDate是yyyy-MM-dd格式
        var currentDate = new Date();
        var date = currentDate.getFullYear() + '-' +
            ('0' + (currentDate.getMonth() + 1)).slice(-2) + '-' +
            ('0' + currentDate.getDate()).slice(-2);
        return date;
    },
    //获取随机数的字符串
    getRandomNum() { 
        var Randnum = '';
        for (var i = 0; i < 10; i++) {
            Randnum += Math.floor(Math.random() * 10);
        };
        return Randnum;
    }

}
export default commentUtil