"use strict";

// is
function is(item) {
  var data = $(item).is($(item));
  return data;
}

$(function () {
  // tab
  $('.tab-list li').click(function () {
    var el = $(this),
        link = el.data('tab');
    el.siblings().removeClass('active');
    el.addClass('active');
    $('.tab-contents .content').each(function () {
      var elc = $(this);

      if (elc.data('content') === link) {
        elc.fadeIn(500).addClass('active');
        elc.siblings().fadeOut(0).removeClass('active');
      }
    });
  });
});
$(function () {
  $('#left-site').each(function () {
    var galleryThumbs = new Swiper($(this).find('.gallery-thumbs'), {
      spaceBetween: 10,
      slidesPerView: 5,
      freeMode: true,
      watchSlidesVisibility: true,
      watchSlidesProgress: true,
      direction: 'vertical'
    });
    var galleryTop = new Swiper($(this).find('.gallery-top'), {
      effect: 'fade',
      navigation: {
        nextEl: $(this).find('.swiper-button-next'),
        prevEl: $(this).find('.swiper-button-prev')
      },
      pagination: {
        el: $(this).find('.swiper-pagination'),
        type: 'fraction'
      },
      autoplay: {
        delay: 5000,
        disableOnInteraction: false
      },
      thumbs: {
        swiper: galleryThumbs
      }
    });
  }); // M.AutoInit();

  $('a').click(function (e) {
    var href = $(this).attr('href');

    if (href.length > 1 && href.split('')[0] == "#") {
      e.preventDefault();
      console.log(href);
      var gotop = $(href).offset().top;
      $('html,body').animate({
        scrollTop: gotop
      }, 1500);
    }
  });

  if (is('#right-site') && is("#left-site")) {
    $(window).on('scroll', function () {
      var event = $('#right-site');
      var leftSiteTop = $('#left-site .pic-container').offset().top;
      var windowTop = $(window).scrollTop();
      var width = $('#wrapper .row').width() / 12 * 4;
      var posR = $(window).width() - $('#wrapper .row').offset().left - width;

      if (windowTop >= leftSiteTop) {
        event.css({
          position: 'fixed',
          width: width,
          top: "-50px",
          left: posR
        });
      } else {
        event.css({
          position: 'relative',
          top: '0px',
          left: 0
        });
      }
    });
  }

  if (is('#message textarea')) {
    $('#message textarea').keyup(function (e) {
      var nums = $(this).val().length;
      $('#message .txt-area .nums i').text(nums);
    });
  }

  $('.fav').click(function () {
    $(this).toggleClass('saved');
    $('#notice').fadeOut();
    setTimeout(function () {
      if (is('.fav.saved')) {
        var txt = "已收藏";
        $('#notice').attr('class', 'successed');
      } else {
        var txt = "取消收藏";
        $('#notice').attr('class', 'failed');
      }

      $('#notice p').text(txt);
      $('#notice').fadeIn();
      setTimeout(function () {
        $('#notice').fadeOut();
      }, 5000);
    }, 1000);
  });
  $('#fav-container #all').click(function () {
    console.log($(this).prop('checked'));

    if ($(this).prop('checked') == true) {
      $('.fav-body .item .select input:checkbox').prop("checked", true);
    } else {
      $('.fav-body .item .select input:checkbox').prop("checked", false);
    }
  });
});