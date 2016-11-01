$(document).ready(function() {
  $('.navbar-btn').on('show.bs.tab', function(event) {
    $('#questionType').val(event.target.hash.substr(1));
  });

  var pageItem = $(".pagination li").not(".prev,.next");
  var prev = $(".pagination li.prev");
  var next = $(".pagination li.next");

  pageItem.click(function() {
    pageItem.removeClass("active");
    $(this).not(".prev,.next").addClass("active");
  });

  next.click(function() {
    $('li.active').removeClass('active').next().addClass('active');
  });

  prev.click(function() {
    $('li.active').removeClass('active').prev().addClass('active');
  });

});

let
value = 2;

function addRadio(divName) {
  let
  newdiv = document.createElement('div');
  newdiv.className = "input-group rd";
  newdiv.innerHTML = "<span class='input-group-addon'><input type='radio' name='sc' value='"
      + value
      + "'></span><input name='rawAnswerTextsSc' type='text' class='form-control'>";
  document.querySelector(divName).appendChild(newdiv);
  value++;
};
function removeRadio(divName) {
  $("div.rd").last().remove();
};

function addCheckbox(divName) {
  let
  newdiv = document.createElement('div');
  newdiv.className = "input-group cb";
  newdiv.innerHTML = "<span class='input-group-addon'><input type='checkbox' name='mc' value='"
      + value
      + "'></span><input name='rawAnswerTextsMc' type='text' class='form-control'>";
  document.querySelector(divName).appendChild(newdiv);
  value++;
};
function removeCheckbox(divName) {
  $("div.cb").last().remove();
};

let
pageNumber = 1;
function addPage(divName) {
  let
  newdiv = document.createElement('li');
  newdiv.className = "active";
  newdiv.innerHTML = "<a href='#'>" + pageNumber + "</a>";
  document.querySelector(divName).appendChild(newdiv);
};
