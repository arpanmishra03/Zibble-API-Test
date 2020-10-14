(function () {
  // event to reset the login field when login is opened
  $("#loginModal").on("show.bs.modal", function () {
    var displayNone = "d-none";
    $("#login-otpSection").addClass(displayNone);
    $("#login-mobileSection").removeClass(displayNone);

    // clear otp input, if any
    $("#loginModal input").val("");
    var mobileInputElement = $("#login-mobileNumber");
    mobileInputElement.removeClass("is-valid");
    mobileInputElement.removeClass("is-invalid");
  });

  // While user is Entering mobile number
  $("#login-mobileNumber").keyup(function (e) {
    login.validateMobileNumber();
  });

  // mobile number entered
  $("#login-sendOtp").click(function (e) {
    // set flag for continous validation
    login.continousPhoneValidation = true;
    var mobileInputElement = $("#login-mobileNumber");
    var displayNone = "d-none";

    if (login.validateMobileNumber()) {
      $("#login-otpSection").removeClass(displayNone);
      $("#login-mobileSection").addClass(displayNone);
      $("#login-mobileNumbeDisplay").html(mobileInputElement.val());
    } else {
      // do nothing
    }
  });

  // function for adding events to the OTP
  $("#login-otpInput input").keyup(function (e) {
    var element = e.currentTarget;
    var inputValue = element.value || "";
    if (inputValue.length === 1) {
      // move to next input block
      $(element).next().focus();
    } else if (inputValue.length > 1) {
      element.value = inputValue.substr(0, 1);
    }
  });

  var login = {
    continousPhoneValidation: false,

    validateMobileNumber: function () {
      var mobileInputElement = $("#login-mobileNumber");
      var invalidClass = "is-invalid";
      var validClass = "is-valid";
      // remove leading zeros, if any
      // This might fail for a few European countries.

      if (
        mobileInputElement[0].validity &&
        mobileInputElement[0].validity.valid
      ) {
        var mobileNumber = mobileInputElement
          .val()
          .replace(/^0+/, "")
          .replace(".", "");
        mobileInputElement.val(mobileNumber);
        if (mobileNumber.length === 10) {
          mobileInputElement.removeClass(invalidClass);
          mobileInputElement.addClass(validClass);
          return true;
        } else {
          return showError();
        }
      } else {
        return showError();
      }

      function showError() {
        if (!this.continousPhoneValidation) {
          // show valid state if the user has entered correct mobile number
          mobileInputElement.removeClass(validClass);
          return false;
        }
        mobileInputElement.addClass(invalidClass);
        mobileInputElement.removeClass(validClass);
        return false;
      }
    },
  };
})();
