#!/usr/bin/env python3

import subprocess

mvn = subprocess.run(['mvn', 'clean', 'install'], cwd='/Users/evan/Dropbox/Development/Neighbor Share/neighbor-share')

scp = subprocess.run(['scp', 'neighborshare-0.0.1-SNAPSHOT.jar',  'neighborshare@neighbor-share.org:/home/neighborshare/neighbor-share'], cwd='/Users/evan/Dropbox/Development/Neighbor Share/neighbor-share/target')

backup = subprocess.run(['ssh', 'neighborshare@neighbor-share.org', 'mv', 'neighbor-share/neighborshare-0.0.2-SNAPSHOT.jar', 'neighbor-share/neighborshare-0.0.2-SNAPSHOT.jar.bak'])

mv = subprocess.run(['ssh', 'neighborshare@neighbor-share.org', 'mv', 'neighbor-share/neighborshare-0.0.1-SNAPSHOT.jar', 'neighbor-share/neighborshare-0.0.2-SNAPSHOT.jar'])

# systemctl restart allowed without password due to that command being added to the /etc/sudoers.d/ directory
restart = subprocess.run(['ssh', 'neighborshare@neighbor-share.org', 'sudo', 'systemctl', 'restart', 'neighborshare.service'])

print(mvn)
print(scp)
print(backup)
print(mv)
print(restart)