const express = require('express');
const fs = require('fs');
const path = require('path');
const zlib = require('zlib');

const app = express();
const port = 3000;
const contentDir = path.join(__dirname, '..', 'content');

app.get('*', (req, res) => {
    let reqPath = req.path;
    if (reqPath === '/') {
        reqPath = '/index.html';
    }

    const host = req.hostname;
    const filePath = path.join(contentDir, host, reqPath);
    const gzPath = filePath + '.gz';
    const metaPath = filePath + '.meta';

    if (!fs.existsSync(gzPath)) {
        return res.status(404).send('Not Found');
    }

    fs.readFile(metaPath, 'utf8', (err, contentType) => {
        if (err) {
            contentType = 'application/octet-stream'; // Default content type
        }

        fs.readFile(gzPath, (err, data) => {
            if (err) {
                return res.status(500).send('Internal Server Error');
            }

            zlib.gunzip(data, (err, decompressed) => {
                if (err) {
                    return res.status(500).send('Internal Server Error');
                }

                res.set('Content-Type', contentType);
                res.send(decompressed);
            });
        });
    });
});

app.listen(port, () => {
    console.log(`Server listening at http://localhost:${port}`);
});