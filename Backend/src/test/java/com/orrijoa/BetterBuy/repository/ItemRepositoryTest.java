package com.orrijoa.BetterBuy.repository;

import com.orrijoa.BetterBuy.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindAll() {
        // Given
        Item item1 = new Item();
        Item item2 = new Item();
        itemRepository.save(item1);
        itemRepository.save(item2);

        // When
        List<Item> items = itemRepository.findAll();

        // Then
        assertThat(items).isNotNull();
        assertThat(items).hasSize(2);

    }
}
