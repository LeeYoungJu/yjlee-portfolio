const express = require('express');
const socket = require('socket.io');
const app = express();
const cors = require('cors');

app.use(cors());
app.use(express.json());

const server = app.listen('3002', () => {
    console.log('server running on port 3001...');
});

const io = socket(server, {cors: {origin: "*"}});

let roomid_list = [0];
let roominfo = {};

io.on('connection', (socket) => {
    console.log(socket.id);

    socket.on('get_roomlist', () => {
        socket.emit('roomlist', roominfo);
    });

    socket.on('make_room', (title) => {
        const new_roomid = Math.max(...roomid_list)+1;
        roominfo[new_roomid] = {title, id:new_roomid};
        roomid_list.push(new_roomid);
        socket.join(new_roomid);
        socket.emit('get_roomid', {title, id:new_roomid});

        io.emit('update_roomlist');
    })

    socket.on('join_room', (data) => {
        socket.join(data);
        console.log('user join room: ' + data);
    });

    socket.on('mts', (data) => {
        console.log(data);
        //socket.broadcast.to(data.id).emit('mtc', data);
        io.to(data.id).emit('mtc', data);
    });

    socket.on('leave', (roomid) => {
        socket.leave(roomid);
    });

    socket.on('disconnect', () => {
        console.log('disconnect');
    });
})