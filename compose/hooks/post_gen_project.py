import os
import glob

from itertools import chain

result = (chain.from_iterable(glob.glob(os.path.join(x[0], '.gitkeep')) for x in os.walk('.')))

for f in result:
    os.remove(f)
