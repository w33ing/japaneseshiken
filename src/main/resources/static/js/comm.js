$(document).ready(function() {

  });

  var kan;
  var hira;
  $('#sub-btn').click(function() {
  var ans = $('#answer').val();
        console.log("ans = "+ans);
        console.log("kan = "+kan);
                   if(ans == hira){
                      $('#status').text("correct");
                      console.log("cor"+hira);
                   }else{
                      $('#status').text("wrong" + hira);
                       console.log("else"+kan);
                   }

            $.ajax({
                url: '/api/random',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    $('#kanji').text(data.kanji);
                    console.log(kan);


                    kan = data.kanji;
                    hira = data.hiragana
                },
                error: function(xhr, status, error) {
                    console.error('Error:', error);
                }

            });
        });
