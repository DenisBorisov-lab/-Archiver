package com.borisov.services.encoding;

import com.borisov.config.SettingsConfiguration;
import com.borisov.models.Node;
import com.borisov.services.EncodingSystem;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class EncodingSystemImpl implements EncodingSystem {
    private String[] letters;
    private Map<String, Integer> dictionary;
    private final String path;

    @Autowired
    public EncodingSystemImpl(SettingsConfiguration configuration) {
        this.path = configuration.getPath();
        letters = readFile(path);
        dictionary = countWords(letters);
    }

    @Override
    public void encode() {
        System.out.println(dictionary);
        crateTree(dictionary);

        // TODO: 01.01.2022 построить дерево для алгоритма Хаффмана
    }

    @SneakyThrows
    public String[] readFile(String path) {
        return new String(Files.readAllBytes(Paths.get(path))).split("");
    }

    public List<Node> crateTree(Map<String, Integer> dict) {
        List<Node> nodes = toNodeList(dict);
        while (nodes.size() != 1) {
            nodes.sort((o1, o2) -> o1.getWeight() - o2.getWeight());
            Node firstNode = nodes.get(0);
            Node secondNode = nodes.get(1);
            nodes.remove(firstNode);
            nodes.remove(secondNode);
            String name = firstNode.getName() + secondNode.getName();
            int weight = firstNode.getWeight() + secondNode.getWeight();
            List<Node> children = new ArrayList<>();
            children.add(firstNode);
            children.add(secondNode);
            nodes.add(new Node(name, weight, children));
        }
        return nodes;


    }

    public Map<String, Integer> countWords(String[] letters) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        Collections.addAll(set, letters);
        int count;
        for (String item : set) {
            count = 0;
            for (String letter : letters) {
                if (item.equals(letter)) {
                    count++;
                }
            }
            map.put(item, count);
        }
        return map;
    }

    public List<Node> toNodeList(Map<String, Integer> dict) {
        Set<String> keys = dict.keySet();
        List<Node> nodes = new ArrayList<>();
        for (String key : keys) {
            nodes.add(new Node(key, dict.get(key), null));
        }
        return nodes;
    }
}
