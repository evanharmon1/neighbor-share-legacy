#!/usr/bin/env python3

import subprocess

mv = subprocess.run(['mv', 'neighborshare-0.0.1-SNAPSHOT.jar', 'neighborshare-0.0.2-SNAPSHOT.jar'], cwd='/home/neighborshare/neighbor-share')

restart_service = subprocess.run(['systemctl', 'restart', 'neighborshare.service'])

print(mv)
print(restart_service)