const express = require('express');
const axios = require('axios');
const cheerio = require('cheerio');

const router = express.Router();

const getHtml = async() => {
    try {
        return await axios.get("https://www.yna.co.kr/industry/agriculture");
    } catch(error) {
        console.error(error);
    }
}

router.get('/news/meet', (req, res, next) => {
    getHtml()
        .then(html => {
            let ulList = [];
            const $ = cheerio.load(html.data);
            const $bodyList = $("div.list-type038 ul").children("li");
            
            $bodyList.each(function(i, elem) {
                ulList[i] = {
                    title: $(this).find('strong.tit-news').text(),
                    url: $(this).find('a.tit-wrap').attr('href'),
                    summary: $(this).find('div.news-con p.lead').text(),
                    date: $(this).find('div.info-box01 span.txt-time').text()
                };
            });

            const data = ulList.filter(n => n.title);
            res.json(data);
        })
        .catch(err => {
            console.log(err);
        });
});

module.exports = router;