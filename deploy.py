#!/usr/bin/env python3

import subprocess

mvn = subprocess.run(['mvn', 'clean', 'install'], cwd='/Users/evan/Dropbox/Development/Neighbor Share/neighbor-share')

scp = subprocess.run(['scp', 'neighborshare-0.0.1-SNAPSHOT.jar',  'neighborshare@neighbor-share.org:/home/neighborshare/neighbor-share'], cwd='/Users/evan/Dropbox/Development/Neighbor Share/neighbor-share/target')

print(mvn)
print(scp)  