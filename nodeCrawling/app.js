const express = require('express');
const cors = require('cors');

const crawlingRouter = require('./routes/crawling');

const app = express();

app.use(cors());
app.use(express.json());

app.set('port', process.env.PORT || 3001);

app.use('/', crawlingRouter);

app.listen(app.get('port'), () => {
    console.log(`server running on ${app.get('port')}`);
})