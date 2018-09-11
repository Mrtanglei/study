var seckill = {
    //封装秒杀相关的ajax的url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        killurl: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/excute';
        }
    },
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            seckillBox.html('秒杀结束!');
        } else if (nowTime < startTime) {
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                seckill.handleSeckillkill(seckillId, seckillBox);
            });
        } else {
            seckill.handleSeckillkill(seckillId, seckillBox);
        }
    },
    handleSeckillkill: function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    $('#killBtn').one('click', function () {
                        $(this).addClass('disable');
                        $.post(seckill.URL.killurl(seckillId, exposer['md5']), {}, function (result) {
                            if (result && result['success']) {
                                node.html('<span class="label label-success">' + result['data']['stateInfo'] + '</span>')
                            } else {
                                console.log(result);
                            }
                        });
                    });
                    node.show();
                } else {
                    seckill.count(seckillId, exposer['now'], exposer['start'], exposer['end'])
                }
            } else {
                console.log(result);
            }
        });
    },
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (parames) {
            //手机验证和登录，计时交互
            var killPhone = $.cookie("killPhone");

            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModel = $('#killPhoneModel');
                killPhoneModel.modal({
                    show: true,
                    backdrop: false,//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log(inputPhone);
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie("killPhone", inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
            }
            var seckillId = parames['seckillId'];
            var startTime = parames['startTime'];
            var endTime = parames['endTime'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log(result);
                }
            });
        }
    }
}