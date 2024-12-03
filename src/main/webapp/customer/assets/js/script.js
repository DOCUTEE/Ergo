(function($) {

  "use strict";

  var initPreloader = function() {
    $(document).ready(function($) {
    var Body = $('body');
        Body.addClass('preloader-site');
    });
    $(window).load(function() {
        $('.preloader-wrapper').fadeOut();
        $('body').removeClass('preloader-site');
    });
  }

  // init Chocolat light box
	var initChocolat = function() {
		Chocolat(document.querySelectorAll('.image-link'), {
		  imageSize: 'contain',
		  loop: true,
		})
	}

  var initSwiper = function() {

    var swiper = new Swiper(".main-swiper", {
      speed: 500,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },
    });

    var category_swiper = new Swiper(".category-carousel", {
      slidesPerView: 6,
      spaceBetween: 30,
      speed: 500,
      navigation: {
        nextEl: ".category-carousel-next",
        prevEl: ".category-carousel-prev",
      },
      breakpoints: {
        0: {
          slidesPerView: 2,
        },
        768: {
          slidesPerView: 3,
        },
        991: {
          slidesPerView: 4,
        },
        1500: {
          slidesPerView: 6,
        },
      }
    });

    var brand_swiper = new Swiper(".brand-carousel", {
      slidesPerView: 4,
      spaceBetween: 30,
      speed: 500,
      navigation: {
        nextEl: ".brand-carousel-next",
        prevEl: ".brand-carousel-prev",
      },
      breakpoints: {
        0: {
          slidesPerView: 2,
        },
        768: {
          slidesPerView: 2,
        },
        991: {
          slidesPerView: 3,
        },
        1500: {
          slidesPerView: 4,
        },
      }
    });

    var products_swiper = new Swiper(".products-carousel", {
      slidesPerView: 5,
      spaceBetween: 30,
      speed: 500,
      navigation: {
        nextEl: ".products-carousel-next",
        prevEl: ".products-carousel-prev",
      },
      breakpoints: {
        0: {
          slidesPerView: 1,
        },
        768: {
          slidesPerView: 3,
        },
        991: {
          slidesPerView: 4,
        },
        1500: {
          slidesPerView: 6,
        },
      }
    });
  }

  var initProductQty = function(){

    $('.product-qty').each(function(){

      var $el_product = $(this);
      var quantity = 0;

      $el_product.find('.quantity-right-plus').click(function(e){
          e.preventDefault();
          var quantity = parseInt($el_product.find('#quantity').val());
          $el_product.find('#quantity').val(quantity + 1);
      });

      $el_product.find('.quantity-left-minus').click(function(e){
          e.preventDefault();
          var quantity = parseInt($el_product.find('#quantity').val());
          if(quantity>0){
            $el_product.find('#quantity').val(quantity - 1);
          }
      });

    });

  }

  // init jarallax parallax
  var initJarallax = function() {
    jarallax(document.querySelectorAll(".jarallax"));

    jarallax(document.querySelectorAll(".jarallax-keep-img"), {
      keepImg: true,
    });
  }

  const initCartHandle = ()=>{
    $('.cart-item').on('click', '.addProductBtn',  function () {
      const row = $(this).closest('.cart-item');
      const cartItemId = row.attr('cartItemId');
      const quantityInput = row.find('.quantity-input').first();
      const quantity = parseInt(quantityInput.val())+1;
      quantityInput.val(quantity)
      updateCartItem({
        cartItemId, quantity, row
      })
    })

    $('.cart-item').on('click', '.subProductBtn',  function () {
      const row = $(this).closest('.cart-item');
      const cartItemId = row.attr('cartItemId');
      const quantityInput = row.find('.quantity-input').first();
      const quantity = parseInt(quantityInput.val())-1;
      if (quantity === 0){
        deleteCartItem({
          cartItemId, row
        })
        return
      }
      quantityInput.val(quantity)
      updateCartItem({
        cartItemId, quantity, row
      })
    })

    $('.cart-item').on('click', '.removeProductBtn',  function () {
      const row = $(this).closest('.cart-item');
      const cartItemId = row.attr('cartItemId');
      deleteCartItem({
        cartItemId, row
      })
    })

    $('.removeAllProductBtn').on('click', function () {
      $('.cart-item').each(function () {
        const row = $(this);
        const cartItemId = row.attr('cartItemId');
        deleteCartItem({
          cartItemId,
          row
        });
        row.remove();
      });
    });

    $('.cart-item').on('change', '.quantity-input',  function () {
      const row = $(this).closest('.cart-item');
      const cartItemId = row.attr('cartItemId');
      const quantity = parseInt($(this).val());
      if (quantity <= 0){
        deleteCartItem({
          cartItemId,
          row
        });
        return
      }
      updateCartItem({
        cartItemId, quantity, row
      })
    })


    const updateCartItem = function ({cartItemId, quantity, row}){
      row.addClass("cart-item__loading")
      const input = row.find('.quantity-input').first()
      $.ajax({
        url: "http://localhost:8080/Ergo/customer/cart",
        method: "POST",
        data: {
          action: "updateQuantity",
          quantity: quantity,
          cartItemId: cartItemId
        },
        success: function(data) {
          const {status, action, oldQuantity} = data
          if (status === "quantityExceed"){
            input.val(oldQuantity)
          }
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.error(jqXHR, textStatus, errorThrown)
          input.val(textStatus?.oldQuantity)
        },
        complete: function (data){
          row.removeClass('cart-item__loading')
          window.location.reload()
        }
      });
    }

    const deleteCartItem = function ({cartItemId, row}){
      row.addClass("cart-item__loading")
      $.ajax({
        url: "http://localhost:8080/Ergo/customer/cart",
        method: "POST",
        data: {
          action: "deleteItem",
          cartItemId: cartItemId
        },
        success: function(data) {
          $(row).remove()
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.error(jqXHR, textStatus, errorThrown)
        },
        complete: function (data){
          window.location.reload()
        }
      });
    }

    const isOutOfStock = async (productId)=>{
       return false
    }
  }

  // document ready
  $(document).ready(function() {
    initPreloader();
    initSwiper();
    initProductQty();
    initJarallax();
    initChocolat();
    initCartHandle();

  }); // End of a document

})(jQuery);