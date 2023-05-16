package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.ShopDTO;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.entities.ShopEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ItemRepository;
import textbasedgame.finalproject.repositories.ShopRepository;

import java.util.Set;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ItemRepository itemRepository;


    public Iterable<ShopEntity> getAll() {

        return this.shopRepository.findAll();

    }


    public ShopEntity add(ShopDTO shopDTO) {

        ShopEntity shop = new ShopEntity();

        shop.setName(shopDTO.getName());

        return this.shopRepository.save(shop);

    }


    @Transactional
    public ShopEntity update(int id, ShopDTO shopDTO) throws NonexistentResourceException {

        ShopEntity shop = this.shopRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Shop doesn't exist", id));

        shop.setName(shopDTO.getName());

        return this.shopRepository.save(shop);

    }


    @Transactional
    public void delete(int id) throws NonexistentResourceException {

        ShopEntity shop = this.shopRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Shop doesn't exist", id));

        this.shopRepository.delete(shop);

    }


    @Transactional
    public void addItemToShop(int itemId, int shopId, int price) throws NonexistentResourceException {

        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));

        ShopEntity shop = this.shopRepository.findById(shopId)
            .orElseThrow(() -> new NonexistentResourceException("Shop doesn't exist", shopId));

        item.getShopItems().add(shop);
        item.setPrice(price);

        this.itemRepository.save(item);

    }


    @Transactional
    public void removeItemFromShop(int itemId, int shopId) throws NonexistentResourceException {

        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));

        ShopEntity shop = this.shopRepository.findById(shopId)
            .orElseThrow(() -> new NonexistentResourceException("Shop doesn't exist", shopId));

        item.getShopItems().remove(shop);
        item.setPrice(0);

        this.itemRepository.save(item);

    }

}
