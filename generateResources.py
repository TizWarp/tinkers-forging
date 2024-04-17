# Stuff!
import json
import os


def del_none(d: dict) -> dict:
    """
    https://stackoverflow.com/a/4256027/4355781
    Modifies input!
    """
    for key, value in list(d.items()):
        if value is None:
            del d[key]
        elif isinstance(value, dict):
            del_none(value)
    return d


def model(filename_parts: tuple, parent: str = 'item/generated', texture: str = None):
    if texture is None:
        texture = '/'.join(filename_parts)
    p = os.path.join('models', 'item', *filename_parts) + '.json'
    os.makedirs(os.path.dirname(p), exist_ok=True)
    with open(p, 'w') as file:
        json.dump(del_none({
            '__comment': 'Generated by generateResources.py function: model',
            'forge_marker': 1,
            'parent': parent,
            'textures': {
                'layer0': '%s:items/%s' % (MOD_ID, texture)
            }
        }), file, indent=2)


def local(s: str) -> str:
    return s.replace('_', ' ').title()


MOD_ID = 'tinkersforging'
TOOLS = [
    'cap',
    'greataxe_spike',
    'greataxe_blade',
    'battlehamer_head',
    'boomerang_head',
    'crossbow_head',
    'dagger_blade',
    'dagger_guard',
    'glaive_blade',
    'glaive_guard',
    'greatsword_blade',
    'greatsword_guard',
    'halberd_blade',
    'halberd_guard',
    'javelin_head',
    'katana_head',
    'lance_head',
    'longbow_head',
    'longsword_blade',
    'longsword_guard',
    'mace_head',
    'parryingdagger_blade',
    "parryingdagger_guard",
    'pike_head',
    'rapier_blade',
    'rapier_guard',
    'saber_blade',
    'saber_guard',
    'scythe_head',
    'spear_head',
    'throwing_axe_head',
    'throwing_knife_head',
    'warhammer_head'
]

if __name__ == '__main__':
    os.chdir('src/main/resources/assets/%s/' % MOD_ID)

    # Tool models
    for tool in TOOLS:
        model((tool,))
        print('item.%s:%s.name=%%s %s' % (MOD_ID, tool, local(tool)))

    # Hammer is special
    model(('hammer',), texture='hammer/metal')