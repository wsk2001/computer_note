var request = require('request');

var srcData = '1234567890123';
var encData = '';
var decData = '';

var reqEncData = {
    'Alias' : 'normal',
    'Plain' : srcData
} 

request({
            method: 'POST',
            url: "http://127.0.0.1:8080/encrypt",
            json: true,
            headers: {
                "content-type": "application/json",
            },
            body: reqEncData
        }, function(error, response, body) {
            encData = response.body.Cipher;

            var reqDecData = {
                'Alias' : 'normal',
                'Cipher' : encData   
            }
                       
            request({
                method: 'POST',
                url: "http://127.0.0.1:8080/decrypt",
                json: true,
                headers: {
                    "content-type": "application/json",
                },
                body: reqDecData
            }, function(error, response, body) {
                decData = response.body.Plain;

                console.log( 'src : ' + srcData);
                console.log( 'enc : ' + encData);
                console.log( 'dec : ' + decData);                
            });

        });
